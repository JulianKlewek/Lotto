package pl.lotto.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import pl.lotto.infrastructure.controller.error.ApiErrorResponse;
import pl.lotto.userauthenticator.dto.UserLoginRequest;
import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

@Tag(name = "Authentication RestController", description = "Microservice responsible for authenticating user")
public interface AuthenticationApi {

    @Operation(summary = "Register user", description = "Creates user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered",
                    content = {@Content(schema = @Schema(implementation = UserRegisterResponse.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "User provided wrong data",
                    content = {@Content(schema = @Schema(implementation = ApiErrorResponse.class),
                            mediaType = "application/json")})
    })
    ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request);

    @Operation(summary = "Login user", description = "Authenticates user and returns jwt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully",
                    content = {@Content(schema = @Schema(implementation = UserLoginResponse.class),
                            mediaType = "application/json")})
    })
    ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request);
}
