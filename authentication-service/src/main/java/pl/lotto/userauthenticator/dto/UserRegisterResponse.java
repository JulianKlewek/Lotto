package pl.lotto.userauthenticator.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record UserRegisterResponse(String username, String email, String message, Instant timestamp) {
}
