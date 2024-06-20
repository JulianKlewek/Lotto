package pl.lotto.numbersgenerator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumbersGeneratorConfiguration {

    @Bean("numberGeneratorClock")
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    WinningNumbersGenerable winningNumbersGenerable() {
        return new WinningNumbersGenerator();
    }

    @Bean
    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacade(WinningNumbersRepository numbersRepository,
                                                                   WinningNumbersGenerable winningNumbersGenerable,
                                                                   @Qualifier("numberGeneratorClock") Clock clock) {
        DrawNumberGenerator drawNumberGenerator = new DrawNumberGenerator(numbersRepository);
        return new NumbersGeneratorFacadeImpl(winningNumbersGenerable, numbersRepository, drawNumberGenerator, clock);
    }

    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacadeForTests(WinningNumbersRepository numbersRepository,
                                                                           WinningNumbersGenerable winningNumbersGenerable,
                                                                           @Qualifier("numberGeneratorClock") Clock clock) {
        return createNumbersGeneratorFacade(numbersRepository, winningNumbersGenerable, clock);
    }
}
