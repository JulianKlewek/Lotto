package pl.lotto.numbersgenerator.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Set;

@Builder
public record WinningNumbersDto(Set<Integer> numbers, ZonedDateTime generatedTime, Long lotteryNumber) {
}
