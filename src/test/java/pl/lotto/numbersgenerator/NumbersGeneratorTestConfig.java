package pl.lotto.numbersgenerator;

import java.time.Clock;

public class NumbersGeneratorTestConfig implements NumbersGeneratorTestConstants{

    WinningNumbersRepositoryTestImpl numbersRepository = new WinningNumbersRepositoryTestImpl();

    Clock clock = Clock.systemUTC();
}
