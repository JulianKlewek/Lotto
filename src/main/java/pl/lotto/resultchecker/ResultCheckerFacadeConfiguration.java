package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

@Configuration
public class ResultCheckerFacadeConfiguration {

    @Bean
    public ResultCheckerFacadeImpl resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                       NumbersGeneratorFacade numbersGeneratorFacade,
                                                       WinningTicketRepository ticketRepository) {
        NumberChecker numberChecker = new NumberChecker();
        return new ResultCheckerFacadeImpl(numberReceiverFacade, numbersGeneratorFacade, numberChecker, ticketRepository);
    }

    public ResultCheckerFacadeImpl resultCheckerFacadeForTests(NumberReceiverFacade numberReceiverFacade,
                                                               NumbersGeneratorFacade numbersGeneratorFacade,
                                                               WinningTicketRepository ticketRepository) {
        return resultCheckerFacade(numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
    }
}
