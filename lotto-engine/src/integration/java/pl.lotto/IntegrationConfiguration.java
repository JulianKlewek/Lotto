package pl.lotto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pl.lotto.numbersgenerator.WinningNumbersGenerable;

import java.time.*;
import java.util.HashSet;
import java.util.List;

@TestConfiguration
public class IntegrationConfiguration {
//
//    @Bean("numberGeneratorClock")
//    Clock clockDrawDateGenerator() {
//        LocalDateTime today = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0, 0);
//        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
//    }

    @Bean("numberReceiverClock")
    Clock clockNumberReceiver() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JUNE, 14, 18, 0, 0);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    @Bean
    @Primary
    WinningNumbersGenerable winningNumbersGenerable() {
        return () -> {
            List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
            return new HashSet<>(integers);
        };
    }

}