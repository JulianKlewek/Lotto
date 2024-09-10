package pl.lotto.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lotto.userauthenticator.UserAuthFacade;
import pl.lotto.userauthenticator.UserDetailsImpl;
import pl.lotto.userauthenticator.dto.*;

import java.nio.CharBuffer;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
@Log4j2
@RequiredArgsConstructor
public class AuthenticationRestController implements AuthenticationApi {

    private final UserAuthFacade userAuthFacade;
    private final AuthenticationManager authenticationManager;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        log.debug("Processing register request for user: {}", request.username());
        UserRegisterResponse response = userAuthFacade.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        log.debug("Processing login request for user: {}", request.username());
        CharSequence passCharSequence = CharBuffer.wrap(request.password());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), passCharSequence));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserLoginResponse loginResponse = userAuthFacade.login(userDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loginResponse);
    }

    @Override
    @GetMapping("/confirm-account")
    public ResponseEntity<EmailConfirmationResponse> confirmEmail(@RequestParam(name = "token") String confirmationToken) {
        log.debug("Processing email confirmation request");
        EmailConfirmationResponse result = userAuthFacade.confirmAccount(confirmationToken);
        HttpStatus httpStatus = result.success()
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, httpStatus);
    }
}