package pl.lotto.numbersgenerator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumbersGeneratorConfig {

    @Bean(name = "numbersGeneratorClock")
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacadeForTests(WinningNumbersRepository numbersRepository,
                                                                           @Qualifier("numbersGeneratorClock") Clock clock) {
        WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator();
        return new NumbersGeneratorFacadeImpl(winningNumbersGenerator, numbersRepository, clock());
    }
}
