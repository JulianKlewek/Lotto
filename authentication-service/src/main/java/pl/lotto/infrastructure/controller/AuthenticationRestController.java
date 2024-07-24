package pl.lotto.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lotto.userauthenticator.UserAuthFacade;
import pl.lotto.userauthenticator.UserDetailsImpl;
import pl.lotto.userauthenticator.dto.UserLoginRequest;
import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

import java.nio.CharBuffer;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController implements AuthenticationApi {

    private final UserAuthFacade userAuthFacade;
    private final AuthenticationManager authenticationManager;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = userAuthFacade.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        CharSequence passCharSequence = CharBuffer.wrap(request.password());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), passCharSequence));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserLoginResponse loginResponse = userAuthFacade.prepareLoginResponse(userDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loginResponse);
    }
}