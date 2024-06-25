package pl.lotto.drawdategenerator.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record DrawDateDto(Instant drawDate) {
}
