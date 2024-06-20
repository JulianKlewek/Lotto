package pl.lotto.numbersgenerator.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record WinningNumbersResponseDto(List<Integer> numbers, Instant drawDate, Long lotteryNumber) {
}
