package pl.lotto.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.userauthenticator.UserAuthFacade;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

@CrossOrigin(origins = "http://localhost:8080")
@RestController("auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final UserAuthFacade userAuthFacade;

    @PostMapping("/signup")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = userAuthFacade.register(request);
        return ResponseEntity.ok(response);
    }
}
