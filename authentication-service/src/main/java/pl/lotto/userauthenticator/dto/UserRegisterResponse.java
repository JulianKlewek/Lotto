package pl.lotto.userauthenticator.dto;

import lombok.Builder;

@Builder
public record UserRegisterResponse(String username, String email) {
}
