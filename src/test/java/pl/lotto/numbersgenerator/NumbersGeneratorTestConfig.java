package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NumbersGeneratorTestConfig implements NumbersGeneratorTestConstants {

    WinningNumbersRepositoryTestImpl numbersRepository = new WinningNumbersRepositoryTestImpl();
    Clock clock = Clock.systemUTC();

    @BeforeAll
    public void fillDatabase() {
        ZonedDateTime drawDate = ZonedDateTime.of(
                2024, 6, 14, 20, 00, 0, 0, ZoneOffset.UTC);
        Instant dateInstant = drawDate.toInstant();
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbersRepository.database.put(100L, new WinningNumbersDetails(winningNumbers, dateInstant, 100L));
    }

}
