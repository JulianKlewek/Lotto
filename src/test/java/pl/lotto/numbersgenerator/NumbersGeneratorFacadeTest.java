package pl.lotto.numbersgenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import static org.assertj.core.api.Assertions.assertThat;

class NumbersGeneratorFacadeTest extends NumbersGeneratorTestConfig {

    @Test
    void should_return_six_random_numbers_in_range_1_to_50() {
        //given
        NumbersGeneratorFacadeImpl numbersGeneratorFacade = new NumbersGeneratorConfig()
                .createNumbersGeneratorFacadeForTests(numbersRepository, clock);
        //when
        WinningNumbersDto generatedWinningNumbers = numbersGeneratorFacade.generateWinningNumbers();
        //then
        assertThat(generatedWinningNumbers.numbers()).hasSize(EXPECTED_AMOUNT_OF_GENERATED_NUMBERS);
        assertThat(generatedWinningNumbers.numbers()).allMatch(i -> i >= MINIMAL_NUMBER && i <= MAXIMUM_NUMBER);
    }

}