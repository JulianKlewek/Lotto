package pl.lotto.infrastructure.controller.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WinningNumbersGeneratorException.class)
    public final ResponseEntity<ApiError> handleException(Exception exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (exception instanceof WinningNumbersGeneratorException winningNumbersNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleWinningNumbersNotFoundException(winningNumbersNotFoundException, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(exception, null, headers, status, request);
        }
    }

    private ResponseEntity<ApiError> handleWinningNumbersNotFoundException(
            WinningNumbersGeneratorException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(exception.getMessage());
        ApiError apiError = new ApiError(errors);
        return handleExceptionInternal(exception, apiError, headers, status, request);
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception exception, ApiError error,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(error, headers, status);
    }
}
