package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    @Test
    void should_return_false_when_user_provided_six_numbers_in_correct_range() {
        //given
        NumberReceiverFacadeImpl numberReceiverFacade = new NumberReceiverFacadeImpl();
        List<Integer> correctNumbersFromUser = List.of(1, 2, 3, 4, 5, 6);

        //when
        NumberReceiverResultDto result = numberReceiverFacade.inputNumbers(correctNumbersFromUser);

        //then
        assertThat(result.error()).isFalse();
    }

    @Test
    void should_return_true_when_user_provided_less_than_six_numbers_in_correct_range() {

    }

    @Test
    void should_return_true_when_user_provided_more_than_six_numbers_in_correct_range() {

    }
}
