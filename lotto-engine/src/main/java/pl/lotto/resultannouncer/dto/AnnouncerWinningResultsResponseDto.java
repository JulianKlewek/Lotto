package pl.lotto.resultannouncer.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record AnnouncerWinningResultsResponseDto(List<Integer> numbers, Instant drawDate, Long lotteryNumber) {
}
