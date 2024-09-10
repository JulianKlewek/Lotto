package pl.lotto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@TestConfiguration
public class IntegrationConfiguration {

    @Bean(name = "confirmationTokenClock")
    Clock clockConfirmationToken() {
        return Clock.systemUTC();
    }

}
