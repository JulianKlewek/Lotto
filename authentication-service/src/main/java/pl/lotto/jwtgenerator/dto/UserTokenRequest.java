package pl.lotto.jwtgenerator.dto;

import lombok.Builder;

@Builder
public record UserTokenRequest(Long id, String tokenType) {
}
