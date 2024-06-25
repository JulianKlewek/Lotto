package pl.lotto.infrastructure.winningnumbersservice.dto;

import java.time.Instant;

public record WinningNumbersRequestDto(Instant drawDate) {
}
