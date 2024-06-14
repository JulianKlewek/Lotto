package pl.lotto.numbersgenerator.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record WinningNumbersDto(Set<Integer> numbers, Instant generatedTime, Long lotteryNumber) {
}
