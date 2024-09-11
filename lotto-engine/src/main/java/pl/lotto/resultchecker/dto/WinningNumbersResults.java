package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record WinningNumbersResults(List<Integer> numbers, Instant drawDate, Long lotteryNumber) {
}
