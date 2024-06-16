package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record BasicTicketInfoResponseDto(boolean won, int matchingNumbersAmount,
                                         Instant drawDate, Long lotteryId) {
}
