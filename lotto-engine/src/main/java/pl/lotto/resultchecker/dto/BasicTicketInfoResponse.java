package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record BasicTicketInfoResponse(boolean won, int matchingNumbersAmount,
                                      Instant drawDate, Long lotteryId) {
}
