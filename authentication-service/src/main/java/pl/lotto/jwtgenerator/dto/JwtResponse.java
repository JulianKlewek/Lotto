package pl.lotto.jwtgenerator.dto;

import lombok.Builder;

@Builder
public record JwtResponse(String token) {
}
