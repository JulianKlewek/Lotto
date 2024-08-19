package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Log4j2
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
        log.info("Scheduled result checking for date [{}]", latestDrawDate);
        resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(latestDrawDate);
    }
}
