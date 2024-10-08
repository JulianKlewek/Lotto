package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.drawdategenerator.dto.DrawDate;
import pl.lotto.numberreceiver.dto.NumberReceiverResult;
import pl.lotto.numberreceiver.dto.TicketPayload;
import pl.lotto.numberreceiver.dto.UserTickets;

import java.time.*;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberReceiverFacadeTest extends NumberReceiverTestConfig {

    @Test
    void should_return_success_when_user_provided_six_numbers_in_correct_range() {
        //given
        List<Integer> correctNumbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 12, 20, 0);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDate.builder().drawDate(instant).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock());
        //when
        NumberReceiverResult result = numberReceiverFacade.inputNumbers(correctNumbersFromUser);
        //then
        assertThat(result.status()).isEqualTo("success");
        assertThat(result.errorsList()).isEmpty();
    }

    @Test
    void should_return_failure_when_user_provided_less_than_six_numbers_in_correct_range() {
        //given
        List<Integer> lessThanCorrectAmountOfNumbers = List.of(1, 2, 3, 4, 5);
        DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 12, 20, 0);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDate.builder().drawDate(instant).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock());
        //when
        NumberReceiverResult result = numberReceiverFacade.inputNumbers(lessThanCorrectAmountOfNumbers);
        //then
        assertThat(result.status()).isEqualTo("failure");
        assertThat(result.errorsList()).contains(ValidationError.LESS_THAN_SIX_NUMBERS.errorMessage);
    }

    @Test
    void should_return_failure_when_user_provided_more_than_six_numbers_in_correct_range() {
        //given
        List<Integer> moreThanCorrectAmountOfNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 12, 20, 0);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDate.builder().drawDate(instant).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock());
        //when
        NumberReceiverResult result = numberReceiverFacade.inputNumbers(moreThanCorrectAmountOfNumbers);
        //then
        assertThat(result.status()).isEqualTo("failure");
        assertThat(result.errorsList()).contains(ValidationError.MORE_THAN_SIX_NUMBERS.errorMessage);
    }

    @Test
    void should_return_failure_when_user_gave_at_least_one_number_out_of_range() {
        //given
        List<Integer> numbersOutOfRange = List.of(1, 2, 3, 4, 6, 100);
        DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 12, 20, 0);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDate.builder().drawDate(instant).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock());
        //when
        NumberReceiverResult result = numberReceiverFacade.inputNumbers(numbersOutOfRange);
        //then
        assertThat(result.status()).isEqualTo("failure");
        assertThat(result.errorsList()).contains(ValidationError.OUT_OF_RANGE.errorMessage);
    }

    @Test
    void should_return_more_than_0_lottery_tickets_for_given_date() {
        //given
        DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDate.builder().drawDate(instant).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock());
        numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        numberReceiverFacade.inputNumbers(List.of(3, 15, 20, 24, 35, 46));
        //when
        UserTickets userTickets = numberReceiverFacade.usersNumbers(instant);
        //then
        List<TicketPayload> tickets = userTickets.tickets();
        assertThat(tickets).hasSizeGreaterThan(0);
    }

    @Test
    void should_return_0_lottery_ticket_for_given_date() {
        //given
        DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn(DrawDate.builder().drawDate(instant).build());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .createNumberReceiverFacadeForTests(ticketRepository, drawDateGeneratorFacade, clock());
        //when
        UserTickets userTickets = numberReceiverFacade.usersNumbers(instant);
        //then
        List<TicketPayload> tickets = userTickets.tickets();
        assertThat(tickets).isEmpty();
    }
}
