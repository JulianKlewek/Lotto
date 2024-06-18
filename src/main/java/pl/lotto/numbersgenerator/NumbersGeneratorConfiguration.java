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
    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacade(WinningNumbersRepository numbersRepository,
                                                                   @Qualifier("numberGeneratorClock") Clock clock) {
        WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator(numbersRepository);
        return new NumbersGeneratorFacadeImpl(winningNumbersGenerator, numbersRepository, clock);
    }

    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacadeForTests(WinningNumbersRepository numbersRepository,
                                                                           @Qualifier("numberGeneratorClock") Clock clock) {
        return createNumbersGeneratorFacade(numbersRepository, clock);
    }
}
