package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import java.time.Instant;

public interface NumbersGeneratorFacade {

    WinningNumbersResponseDto generateWinningNumbers();

    WinningNumbersResponseDto getWinningNumbersForDate(Instant request);

    WinningNumbersResponseDto getWinningNumbersForLotteryNumber(Long lotteryNumber);

    Instant getLatestDrawDateWithGeneratedNumbers();
}
