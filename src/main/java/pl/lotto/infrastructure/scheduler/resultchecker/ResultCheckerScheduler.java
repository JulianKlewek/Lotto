package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.Instant;

@AllArgsConstructor
@Component
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @Scheduled(cron = "${lotto.result-checker.scheduler-cron}")
    public void checkResults() {
        Instant latestDrawDate = numbersGeneratorFacade.getLatestDrawDateWithGeneratedNumbers();
        resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(latestDrawDate);
    }
}
