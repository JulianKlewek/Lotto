package pl.lotto.infrastructure.scheduler.numbersgenerator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

@AllArgsConstructor
@Component
public class NumbersGeneratorScheduler {

    private final NumbersGeneratorFacade numbersGeneratorFacade;

//    @Scheduled(cron = "${lotto.number-generator.scheduler-cron}")
//    public void generateWinningNumbers() {
//        numbersGeneratorFacade.generateWinningNumbers();
//    }
}
