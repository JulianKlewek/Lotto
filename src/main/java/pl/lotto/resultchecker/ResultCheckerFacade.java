package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.ZonedDateTime;

public interface ResultCheckerFacade {
    WinningTicketsDto getAllWinningTicketsForGivenDrawDate(ZonedDateTime drawDate);
}