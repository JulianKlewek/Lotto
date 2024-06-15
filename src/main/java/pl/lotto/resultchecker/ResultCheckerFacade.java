package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;

public interface ResultCheckerFacade {
    WinningTicketsDto getAllWinningTicketsForGivenDrawDate(Instant drawDate);
}