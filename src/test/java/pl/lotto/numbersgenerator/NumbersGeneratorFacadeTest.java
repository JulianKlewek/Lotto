package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class NumbersGeneratorFacadeTest extends NumbersGeneratorTestConfig {

    @RepeatedTest(10)
    void should_return_six_random_numbers_in_range_1_to_50() {
        //given
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfiguration()
                .createNumbersGeneratorFacadeForTests(numbersRepository, clock);
        //when
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.generateWinningNumbers();
        //then
        Set<Integer> winningNumbersSet = winningNumbersDto.numbers();
        Long lotteryNumber = winningNumbersDto.lotteryNumber();
        assertAll(
                () -> assertThat(winningNumbersSet)
                        .hasSize(EXPECTED_AMOUNT_OF_GENERATED_NUMBERS)
                        .allMatch(this::isInRange),
                () -> assertThat(lotteryNumber)
                        .isEqualTo(101L)
        );
    }

    private boolean isInRange(Integer number) {
        return number >= MINIMAL_NUMBER && number <= MAXIMUM_NUMBER;
    }

    @Test
    void should_return_correct_winning_numbers_for_given_draw_date() {
        //given
        long expectedLotteryNumber = 100L;
        HashSet<Integer> expectedWinningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfiguration()
                .createNumbersGeneratorFacadeForTests(numbersRepository, clock);
        ZonedDateTime drawDate = ZonedDateTime.of(
                2024, 6, 14, 20, 0, 0, 0, ZoneOffset.UTC);
        Instant expectedDrawDate = drawDate.toInstant();
        numbersGeneratorFacade.generateWinningNumbers();
        //when
        WinningNumbersDto winningNumbers = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        //then
        Instant actualDrawDate = winningNumbers.generatedTime();
        Long actualLotteryNumber = winningNumbers.lotteryNumber();
        Set<Integer> actualWinningNumbers = winningNumbers.numbers();
        assertAll(
                () -> assertThat(actualDrawDate).isEqualTo(expectedDrawDate),
                () -> assertThat(actualLotteryNumber).isEqualTo(expectedLotteryNumber),
                () -> assertThat(actualWinningNumbers).isEqualTo(expectedWinningNumbers)
        );
    }

    @Test
    void should_throw_winning_numbers_not_found_exception_for_given_draw_date() {
        //given
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfiguration()
                .createNumbersGeneratorFacadeForTests(numbersRepository, clock);
        ZonedDateTime drawDate = ZonedDateTime.of(
                2024, 6, 15, 20, 0, 0, 0, ZoneOffset.UTC);

        //when
        //then
        assertThatExceptionOfType(WinningNumbersNotFoundException.class)
                .isThrownBy(() -> numbersGeneratorFacade.getWinningNumbersForDate(drawDate))
                .withMessage("Winning numbers not found");
    }
}