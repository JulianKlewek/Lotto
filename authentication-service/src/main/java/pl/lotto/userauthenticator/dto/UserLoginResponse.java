package pl.lotto.userauthenticator.dto;

import lombok.Builder;

@Builder
public record UserLoginResponse(UserInfoResponse user, String accessToken) {
}
