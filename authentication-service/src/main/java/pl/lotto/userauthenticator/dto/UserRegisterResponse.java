package pl.lotto.userauthenticator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserRegisterResponse(
        @Schema(description = "Username", example = "User1")
        String username,
        @Schema(description = "Email", example = "user1@gmail.com")
        String email,
        @Schema(description = "Registration result message", example = "ACCOUNT CREATED")
        String message,
        @Schema(description = "Date of account registration", example = "2024-06-07T21:20:32Z")
        Instant timestamp) {
}
