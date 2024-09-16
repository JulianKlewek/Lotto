package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersResponse;

import java.time.Instant;

public interface NumbersGeneratorFacade {

    WinningNumbersResponse generateWinningNumbers();

    WinningNumbersResponse getWinningNumbersForDate(Instant request);

    WinningNumbersResponse getWinningNumbersForLotteryNumber(Long lotteryNumber);

    Instant getLatestDrawDateWithGeneratedNumbers();
}
