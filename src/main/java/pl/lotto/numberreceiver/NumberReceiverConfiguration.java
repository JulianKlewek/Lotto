package pl.lotto.numberreceiver;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {

    @Bean(name = "numberReceiverClock")
    Clock clock() {
        return Clock.systemUTC();
    }

    public NumberReceiverFacade createNumberReceiverFacadeForTests(TicketRepository ticketRepository,
                                                                   DrawDateGeneratorFacade drawDateGenerator,
                                                                   @Qualifier("numberReceiverClock") Clock clock) {
        NumberReceiverValidator numberReceiverValidator = new NumberReceiverValidator();
        HashGenerator hashGenerator = new HashGenerator();
        return new NumberReceiverFacadeImpl(numberReceiverValidator, hashGenerator, ticketRepository, drawDateGenerator, clock);
    }

    @Bean("numberReceiverFacadeProd")
    public NumberReceiverFacade createNumberReceiverFacade(TicketRepository ticketRepository,
                                                                        DrawDateGeneratorFacade drawDateGenerator,
                                                                        @Qualifier("numberReceiverClock") Clock clock) {
        NumberReceiverValidator numberReceiverValidator = new NumberReceiverValidator();
        HashGenerator hashGenerator = new HashGenerator();
        return new NumberReceiverFacadeImpl(numberReceiverValidator, hashGenerator, ticketRepository, drawDateGenerator, clock);
    }
}
