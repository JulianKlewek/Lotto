package pl.lotto.numberreceiver;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {


    @Bean("numberReceiverClock")
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean("numberReceiverFacadeProd")
    public NumberReceiverFacade createNumberReceiverFacade(TicketRepository ticketRepository,
                                                           DrawDateGeneratorFacade drawDateGenerator,
                                                           @Qualifier("numberReceiverClock") Clock clock) {
        NumberReceiverValidator numberReceiverValidator = new NumberReceiverValidator();
        HashGenerator hashGenerator = new HashGenerator();
        return new NumberReceiverFacadeImpl(numberReceiverValidator, hashGenerator, ticketRepository, drawDateGenerator, clock);
    }

    public NumberReceiverFacade createNumberReceiverFacadeForTests(TicketRepository ticketRepository,
                                                                   DrawDateGeneratorFacade drawDateGenerator,
                                                                   @Qualifier("numberReceiverClock") Clock clock) {
        return createNumberReceiverFacade(ticketRepository, drawDateGenerator, clock);
    }
}
