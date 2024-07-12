package pl.lotto.jwtgenerator.dto;

import lombok.Builder;

@Builder
public record UserTokenRequest(String username, String tokenType) {
}
