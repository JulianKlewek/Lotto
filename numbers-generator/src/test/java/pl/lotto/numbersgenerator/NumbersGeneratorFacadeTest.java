package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;


class NumbersGeneratorFacadeTest extends NumbersGeneratorTestConfig {

    @RepeatedTest(10)
    void should_return_six_random_numbers_in_range_1_to_50() {
        //given
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfiguration()
                .createNumbersGeneratorFacadeForTests(numbersRepository, winningNumbersGenerable, clock);
        //when
        WinningNumbersResponseDto winningNumbersDto = numbersGeneratorFacade.generateWinningNumbers();
        //then
        List<Integer> winningNumbersSet = winningNumbersDto.numbers();
        Long lotteryNumber = winningNumbersDto.lotteryNumber();
        assertAll(
                () -> assertThat(winningNumbersSet)
                        .hasSize(EXPECTED_AMOUNT_OF_GENERATED_NUMBERS)
                        .allMatch(this::isInRange),
                () -> assertThat(lotteryNumber)
                        .isEqualTo(101L));
    }

    private boolean isInRange(Integer number) {
        return number >= MINIMAL_NUMBER && number <= MAXIMUM_NUMBER;
    }

    @Test
    void should_return_correct_winning_numbers_for_given_draw_date() {
        //given
        long expectedLotteryNumber = 100L;
        List<Integer> expectedWinningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfiguration()
                .createNumbersGeneratorFacadeForTests(numbersRepository, winningNumbersGenerable, clock);
        Instant drawDate = Instant.parse("2024-06-14T20:00:00.00Z");
        numbersGeneratorFacade.generateWinningNumbers();
        //when
        WinningNumbersResponseDto winningNumbers = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        //then
        Instant actualDrawDate = winningNumbers.drawDate();
        Long actualLotteryNumber = winningNumbers.lotteryNumber();
        List<Integer> actualWinningNumbers = winningNumbers.numbers();
        assertAll(
                () -> assertThat(actualDrawDate).isEqualTo(drawDate),
                () -> assertThat(actualLotteryNumber).isEqualTo(expectedLotteryNumber),
                () -> assertThat(actualWinningNumbers).isEqualTo(expectedWinningNumbers));
    }

    @Test
    void should_throw_winning_numbers_not_found_exception_for_given_draw_date() {
        //given
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfiguration()
                .createNumbersGeneratorFacadeForTests(numbersRepository, winningNumbersGenerable, clock);
        Instant drawDate = Instant.parse("2024-06-15T20:15:30.00Z");
        //when
        //then

        assertThatExceptionOfType(WinningNumbersNotFoundException.class)
                .isThrownBy(() -> numbersGeneratorFacade.getWinningNumbersForDate(drawDate))
                .withMessage("Winning numbers not found");
    }
}