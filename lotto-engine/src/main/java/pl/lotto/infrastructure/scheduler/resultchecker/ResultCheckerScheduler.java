package pl.lotto.infrastructure.scheduler.resultchecker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Component
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;
    private final Clock clock;

    public ResultCheckerScheduler(ResultCheckerFacade resultCheckerFacade, @Qualifier("resultCheckerScheduler") Clock clock) {
        this.resultCheckerFacade = resultCheckerFacade;
        this.clock = clock;
    }

    @Scheduled(cron = "${lotto.result-checker.scheduler-cron}")
    public void checkResults() {
        Instant latestDrawDate = Instant.now(clock)
                .truncatedTo(ChronoUnit.MINUTES);
        resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(latestDrawDate);
    }
}
