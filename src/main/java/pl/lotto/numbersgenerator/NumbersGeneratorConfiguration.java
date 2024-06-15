package pl.lotto.numbersgenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumbersGeneratorConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacade(WinningNumbersRepository numbersRepository,
                                                                   Clock clock) {
        WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator(numbersRepository);
        return new NumbersGeneratorFacadeImpl(winningNumbersGenerator, numbersRepository, clock);
    }

    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacadeForTests(WinningNumbersRepository numbersRepository,
                                                                           Clock clock) {
        return createNumbersGeneratorFacade(numbersRepository, clock);
    }
}
