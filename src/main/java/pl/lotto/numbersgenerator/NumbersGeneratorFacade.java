package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.Instant;

public interface NumbersGeneratorFacade {

    WinningNumbersDto generateWinningNumbers();

    WinningNumbersDto getWinningNumbersForDate(Instant drawDate);
}
