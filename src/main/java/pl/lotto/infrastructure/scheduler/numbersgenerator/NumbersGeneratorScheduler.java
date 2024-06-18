package pl.lotto.infrastructure.scheduler.numbersgenerator;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

@AllArgsConstructor
@Component
public class NumbersGeneratorScheduler {

    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @Scheduled(cron = "*/2 * * * * *")
    public void generateWinningNumbers(){
        numbersGeneratorFacade.generateWinningNumbers();
    }
}
