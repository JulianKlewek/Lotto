package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.ZonedDateTime;

public interface NumbersGeneratorFacade {

    WinningNumbersDto generateWinningNumbers();

    WinningNumbersDto getWinningNumbersForDate(ZonedDateTime drawDate);
}
