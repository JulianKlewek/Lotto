package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record WinningTicketPayload(String hash, List<Integer> userNumbers, Instant drawDate,
                                   Long lotteryNumber, int amountOfCorrectNumbers) {
}
