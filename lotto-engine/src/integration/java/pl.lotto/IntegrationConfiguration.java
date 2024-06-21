package pl.lotto;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.*;

import static pl.lotto.BaseIntegrationTest.wireMockServer;

@TestConfiguration
@Log4j2
public class IntegrationConfiguration {

    @Bean("numberReceiverClock")
    Clock clockNumberReceiver() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JUNE, 14, 18, 0, 0);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }
    @AfterEach
    void afterEach() {
        log.info("Resetting wireMock");
        wireMockServer.resetAll();
    }
}
