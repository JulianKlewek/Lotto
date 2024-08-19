package pl.lotto.numberreceiver;

import org.junit.jupiter.api.AfterEach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

class NumberReceiverTestConfig {

    protected final TicketRepository ticketRepository = new InMemoryTicketRepository();

    @Bean("numberReceiverClock")
    @Primary
    Clock clock() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JUNE, 13, 11, 11, 11);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    @AfterEach
    public void afterEach() {
        ticketRepository.deleteAll();
    }
}
