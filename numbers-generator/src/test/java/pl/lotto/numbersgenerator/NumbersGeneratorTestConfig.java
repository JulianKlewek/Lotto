package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
class NumbersGeneratorTestConfig implements NumbersGeneratorTestConstants {

    protected final InMemoryWinningNumbersRepository numbersRepository = new InMemoryWinningNumbersRepository();
    protected final WinningNumbersPropertyConfigurable propertyConfigurable = new WinningNumbersPropertyTestConfig(1, 50, 6);
    protected final WinningNumbersGenerable winningNumbersGenerable = new WinningNumbersGenerator(propertyConfigurable);
    protected final Clock clock = Clock.systemUTC();

    @BeforeEach
    public void fillDatabase() {
        numbersRepository.deleteAll();
        ZonedDateTime drawDate = ZonedDateTime.of(
                2024, 6, 14, 20, 0, 0, 0, ZoneOffset.UTC);
        Instant dateInstant = drawDate.toInstant();
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbersRepository.save(new WinningNumbersDetails(winningNumbers, dateInstant, 100L));
    }

}
