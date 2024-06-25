package pl.lotto.resultannouncer.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record AnnouncerTicketDto(String hash, List<Integer> userNumbers, Instant drawDate,
                                 Long lotteryNumber, int amountOfCorrectNumbers) {
}
