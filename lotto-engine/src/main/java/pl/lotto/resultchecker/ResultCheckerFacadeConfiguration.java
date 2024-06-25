package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class ResultCheckerFacadeConfiguration {

    @Bean
    public ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                       WinningNumbersPort winningNumbersPort,
                                                       WinningTicketRepository ticketRepository) {
        NumberChecker numberChecker = new NumberChecker();
        CheckerResponseGenerator checkerResponseGenerator = new CheckerResponseGenerator(numberChecker);
        NobodyWonTicketGenerator nobodyWonTicketGenerator = new NobodyWonTicketGenerator();
        return new ResultCheckerFacadeImpl(numberReceiverFacade, winningNumbersPort,
                numberChecker, ticketRepository, checkerResponseGenerator, nobodyWonTicketGenerator);
    }

    public ResultCheckerFacade resultCheckerFacadeForTests(NumberReceiverFacade numberReceiverFacade,
                                                               WinningNumbersPort numbersGeneratorFacade,
                                                               WinningTicketRepository ticketRepository) {
        return resultCheckerFacade(numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
    }
}
