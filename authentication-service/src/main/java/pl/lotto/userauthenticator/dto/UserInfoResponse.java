package pl.lotto.userauthenticator.dto;

import lombok.Builder;

@Builder
public record UserInfoResponse(Long id, String username, String email) {
}
