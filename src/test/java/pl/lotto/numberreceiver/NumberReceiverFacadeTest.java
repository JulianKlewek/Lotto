package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class NumberReceiverFacadeTest extends NumberReceiverTestConfig {

    @Test
    void should_return_success_when_user_provided_six_numbers_in_correct_range() {
        //given
        DrawDateGeneratorFacade drawDateGeneratorFacade = Mockito.mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = LocalDateTime.of(2024, Month.JUNE, 12, 20, 0);
        ZonedDateTime drawDate = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Mockito.when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDateDto.builder().drawDate(drawDate).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock);
        Set<Integer> correctNumbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(correctNumbersFromUser);
        //then
        assertThat(result.status()).isEqualTo("success");
        assertThat(result.errorsList()).isEmpty();
    }

    @Test
    void should_return_failure_when_user_provided_less_than_six_numbers_in_correct_range() {
        //given
        Set<Integer> lessThanCorrectAmountOfNumbers = Set.of(1, 2, 3, 4, 5);
        DrawDateGeneratorFacade drawDateGeneratorFacade = Mockito.mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = LocalDateTime.of(2024, Month.JUNE, 12, 20, 0);
        ZonedDateTime drawDate = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Mockito.when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDateDto.builder().drawDate(drawDate).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock);
        //when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(lessThanCorrectAmountOfNumbers);
        //then
        assertThat(result.status()).isEqualTo("failure");
        assertThat(result.errorsList()).contains(ValidationError.LESS_THAN_SIX_NUMBERS.errorMessage);
    }

    @Test
    void should_return_failure_when_user_provided_more_than_six_numbers_in_correct_range() {
        //given
        Set<Integer> moreThanCorrectAmountOfNumbers = Set.of(1, 2, 3, 4, 5, 6, 7);
        DrawDateGeneratorFacade drawDateGeneratorFacade = Mockito.mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = LocalDateTime.of(2024, Month.JUNE, 12, 20, 0);
        ZonedDateTime drawDate = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Mockito.when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDateDto.builder().drawDate(drawDate).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock);
        //when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(moreThanCorrectAmountOfNumbers);
        //then
        assertThat(result.status()).isEqualTo("failure");
        assertThat(result.errorsList()).contains(ValidationError.MORE_THAN_SIX_NUMBERS.errorMessage);
    }

    @Test
    void should_return_failure_when_user_gave_at_least_one_number_out_of_range() {
        //given
        Set<Integer> numbersOutOfRange = Set.of(1, 2, 3, 4, 6, 100);
        DrawDateGeneratorFacade drawDateGeneratorFacade = Mockito.mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = LocalDateTime.of(2024, Month.JUNE, 12, 20, 0);
        ZonedDateTime drawDate = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Mockito.when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDateDto.builder().drawDate(drawDate).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock);
        //when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(numbersOutOfRange);
        //then
        assertThat(result.status()).isEqualTo("failure");
        assertThat(result.errorsList()).contains(ValidationError.OUT_OF_RANGE.errorMessage);
    }
}
