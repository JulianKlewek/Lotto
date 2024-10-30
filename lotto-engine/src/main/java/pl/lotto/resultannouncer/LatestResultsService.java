package pl.lotto.resultannouncer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDate;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.time.Clock;
import java.time.Instant;

@Log4j2
@Service
class LatestResultsService {

    private final WinningNumbersPort winningNumbersPort;
    private final DrawDateGeneratorFacade drawDateGeneratorFacade;
    private final Clock clock;

    public LatestResultsService(WinningNumbersPort winningNumbersPort,
                                DrawDateGeneratorFacade drawDateGeneratorFacade,
                                @Qualifier("resultAnnouncerClock") Clock clock) {
        this.winningNumbersPort = winningNumbersPort;
        this.drawDateGeneratorFacade = drawDateGeneratorFacade;
        this.clock = clock;
    }

    @Deprecated
    public WinningNumbersResponse getLatestDrawResults() {
        Instant now = clock.instant();
        DrawDate latestDrawDate = drawDateGeneratorFacade.getLatestDrawDate(now);
        log.debug("Fetching latest lottery results for date: [{}]", now);
        return winningNumbersPort.getWinningNumbersForDate(latestDrawDate.drawDate());
    }

    public WinningNumbersResponse getLatestDrawWinningNumbers(){
        // TODO: 26/09/2024 DODAC REDIS ZEBY NON STOP NIE STRZELAŁO DO EXTERNAL SERWISU
        log.debug("Fetching latest lottery results");
        return winningNumbersPort.getLatestWinningNumbers();
    }
}
