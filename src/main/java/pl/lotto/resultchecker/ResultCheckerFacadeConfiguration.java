package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

@Configuration
public class ResultCheckerFacadeConfiguration {

    @Bean
    public ResultCheckerFacadeImpl resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                       NumbersGeneratorFacade numbersGeneratorFacade) {
        return new ResultCheckerFacadeImpl(numberReceiverFacade, numbersGeneratorFacade);
    }

    public ResultCheckerFacadeImpl resultCheckerFacadeForTests(NumberReceiverFacade numberReceiverFacade,
                                                               NumbersGeneratorFacade numbersGeneratorFacade) {
        return new ResultCheckerFacadeImpl(numberReceiverFacade, numbersGeneratorFacade);
    }
}
