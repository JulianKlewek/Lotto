package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.time.Instant;

@AllArgsConstructor
@Component
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;
    private final WinningNumbersPort winningNumbersPort;

    @Scheduled(cron = "${lotto.result-checker.scheduler-cron}")
    public void checkResults() {
        Instant latestDrawDate = winningNumbersPort.getLatestDrawDateWithGeneratedNumbers();
        resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(latestDrawDate);
    }
}
