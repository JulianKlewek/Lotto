package pl.lotto.infrastructure.winningnumbersservice.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record WinningNumbersResponse(List<Integer> numbers, Instant drawDate, Long lotteryNumber) {
}
