package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import java.time.Instant;

public interface NumbersGeneratorFacade {

    WinningNumbersResponseDto generateWinningNumbers();

    WinningNumbersResponseDto getWinningNumbersForDate(Instant drawDate);

    WinningNumbersResponseDto getWinningNumbersForLotteryNumber(Long lotteryNumber);

    Instant getLatestDrawDateWithGeneratedNumbers();
}
