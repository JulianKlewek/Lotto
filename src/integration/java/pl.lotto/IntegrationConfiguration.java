package pl.lotto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

@TestConfiguration
public class IntegrationConfiguration {

    @Bean("numberGeneratorClock")
    @Primary
    Clock clockDrawDateGenerator() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0, 0);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    @Bean("numberReceiverClock")
    @Primary
    Clock clockNumberReceiver() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JUNE, 14, 18, 0, 0);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

}
