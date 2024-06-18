package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;

@AllArgsConstructor
@Component
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;

    @Scheduled(cron = "*/2 * * * * *")
    public void checkResults() {
//        resultCheckerFacade.isSystemGeneratedResults()
    }
}
