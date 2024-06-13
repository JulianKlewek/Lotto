package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    public NumberReceiverFacade createNumberReceiverFacadeForTests(TicketRepository ticketRepository,
                                                                   DrawDateGeneratorFacade drawDateGenerator,
                                                                   Clock clock) {
        NumberReceiverValidator numberReceiverValidator = new NumberReceiverValidator();
        HashGenerator hashGenerator = new HashGenerator();
        return new NumberReceiverFacadeImpl(numberReceiverValidator, hashGenerator, ticketRepository, drawDateGenerator, clock);
    }

    @Bean("numberReceiverFacadeProd")
    public NumberReceiverFacade createNumberReceiverFacadeForProduction(TicketRepository ticketRepository,
                                                                        DrawDateGeneratorFacade drawDateGenerator,
                                                                        Clock clock) {
        NumberReceiverValidator numberReceiverValidator = new NumberReceiverValidator();
        HashGenerator hashGenerator = new HashGenerator();
        return new NumberReceiverFacadeImpl(numberReceiverValidator, hashGenerator, ticketRepository, drawDateGenerator, clock);
    }
}
