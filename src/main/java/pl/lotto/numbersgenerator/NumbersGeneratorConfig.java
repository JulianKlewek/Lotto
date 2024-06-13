package pl.lotto.numbersgenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumbersGeneratorConfig {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public NumbersGeneratorFacadeImpl createNumbersGeneratorFacadeForTests(WinningNumbersRepository numbersRepository,
                                                                           Clock clock){
        WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator();
        return new NumbersGeneratorFacadeImpl(winningNumbersGenerator, numbersRepository, clock());
    }
}
