package pl.lotto.infrastructure.controller.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import pl.lotto.userauthenticator.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class GlobalExceptionHandler {

    static final String VALIDATION_FAILED_MSG = "VALIDATION FAILED";

    @ExceptionHandler({MethodArgumentNotValidException.class, UserAlreadyExistsException.class})
    public final ResponseEntity<ApiErrorResponse> handleException(Exception exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (exception instanceof MethodArgumentNotValidException argumentNotValidException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleValidationException(argumentNotValidException, headers, status, request);
        } else if (exception instanceof UserAlreadyExistsException userAlreadyExistsException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleUserAlreadyExistsException(userAlreadyExistsException, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(exception, null, headers, status, request);
        }
    }

    private ResponseEntity<ApiErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        if (exception.getMessage().startsWith("Email")) {
            errors.put("email", exception.getMessage());
        } else {
            errors.put("username", exception.getMessage());
        }
        ApiErrorResponse apiError = new ApiErrorResponse(VALIDATION_FAILED_MSG, errors);
        return handleExceptionInternal(exception, apiError, headers, status, request);
    }

    private ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            if (errors.containsKey(error.getField())) {
                errors.put(error.getField(), String.format("%s, %s", errors.get(error.getField()), error.getDefaultMessage()));
            } else {
                errors.put(error.getField(), error.getDefaultMessage());
            }
        });
        ApiErrorResponse apiError = new ApiErrorResponse(VALIDATION_FAILED_MSG, errors);
        return handleExceptionInternal(exception, apiError, headers, status, request);

    }

    private ResponseEntity<ApiErrorResponse> handleExceptionInternal(
            Exception exception, ApiErrorResponse error, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, RequestAttributes.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(error, headers, status);
    }
}
