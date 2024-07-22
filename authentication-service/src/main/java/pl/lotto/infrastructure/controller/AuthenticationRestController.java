package pl.lotto.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.userauthenticator.UserAuthFacade;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController implements AuthenticationApi {

    private final UserAuthFacade userAuthFacade;

    @PostMapping("/signup")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = userAuthFacade.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}