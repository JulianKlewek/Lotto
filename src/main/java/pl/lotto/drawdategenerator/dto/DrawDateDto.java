package pl.lotto.drawdategenerator.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Builder
public record DrawDateDto(ZonedDateTime drawDate) {
}
