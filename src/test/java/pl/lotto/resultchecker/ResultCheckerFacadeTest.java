package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;
import pl.lotto.resultchecker.dto.BasicTicketInfoResponseDto;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

class ResultCheckerFacadeTest extends ResultCheckerFacadeTestConfig {

    @Test
    void should_return_four_winners() {
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        when(numbersGeneratorFacade.getWinningNumbersForDate(drawDate)).thenReturn(
                new WinningNumbersDto(List.of(11, 12, 21, 39, 40, 41), drawDate, 2L));
        when(numberReceiverFacade.usersNumbers(any())).thenReturn(
                new UserTicketsDto(List.of(
                        new TicketDto("hash1", List.of(11, 12, 21, 39, 40, 41), drawDate, ""),
                        new TicketDto("hash2", List.of(11, 12, 21, 39, 40, 41), drawDate, ""),
                        new TicketDto("hash3", List.of(11, 12, 21, 39, 40, 1), drawDate, ""),
                        new TicketDto("hash4", List.of(11, 12, 21, 39, 1, 2), drawDate, ""),
                        new TicketDto("hash5", List.of(11, 12, 21, 1, 2, 3), drawDate, ""),
                        new TicketDto("hash6", List.of(11, 12, 3, 4, 5, 6), drawDate, ""),
                        new TicketDto("hash7", List.of(1, 2, 3, 4, 5, 6), drawDate, "")
                ))
        );
        //when
        WinningTicketsDto winningTickets = resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(drawDate);
        //then
        List<WinningTicketDto> winnersWithSixCorrectNumbers = winningTickets.winningTickets().stream()
                .filter(e -> e.amountOfCorrectNumbers() == 6)
                .toList();
        List<WinningTicketDto> winnersWithFiveCorrectNumbers = winningTickets.winningTickets().stream()
                .filter(e -> e.amountOfCorrectNumbers() == 5)
                .toList();
        List<WinningTicketDto> winnersWithFourCorrectNumbers = winningTickets.winningTickets().stream()
                .filter(e -> e.amountOfCorrectNumbers() == 4)
                .toList();
        int numberOfWinners = winningTickets.winningTickets().size();
        assertAll(
                () -> assertThat(numberOfWinners).isEqualTo(4),
                () -> assertThat(winnersWithSixCorrectNumbers).hasSize(2),
                () -> assertThat(winnersWithFiveCorrectNumbers).hasSize(1),
                () -> assertThat(winnersWithFourCorrectNumbers).hasSize(1)
        );
    }

    @Test
    void should_biggest_winners_tickets_contain_all_winning_numbers() {
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(11, 12, 21, 39, 40, 41);
        when(numbersGeneratorFacade.getWinningNumbersForDate(drawDate)).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        when(numberReceiverFacade.usersNumbers(any())).thenReturn(
                new UserTicketsDto(List.of(
                        new TicketDto("hash1", List.of(11, 12, 21, 39, 40, 41), drawDate, ""),
                        new TicketDto("hash2", List.of(11, 12, 21, 39, 40, 41), drawDate, ""),
                        new TicketDto("hash3", List.of(11, 12, 21, 39, 40, 1), drawDate, ""),
                        new TicketDto("hash4", List.of(11, 12, 21, 39, 1, 2), drawDate, ""),
                        new TicketDto("hash5", List.of(11, 12, 21, 1, 2, 3), drawDate, ""),
                        new TicketDto("hash6", List.of(11, 12, 3, 4, 5, 6), drawDate, ""),
                        new TicketDto("hash7", List.of(1, 2, 3, 4, 5, 6), drawDate, "")
                ))
        );
        //when
        WinningTicketsDto winningTickets = resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(drawDate);
        //then
        List<WinningTicketDto> winnersWithSixCorrectNumbers = winningTickets.winningTickets().stream()
                .filter(e -> e.amountOfCorrectNumbers() == 6)
                .toList();
        assertThat(winnersWithSixCorrectNumbers)
                .hasSize(2)
                .extracting(WinningTicketDto::userNumbers)
                .containsOnly(winningNumbersList);
    }

    @Test
    void should_return_ticket_with_status_PRIZE_NOT_RECEIVED_for_given_hash(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers, Instant.now(), 1L, 5, false);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers, Instant.now(), 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        TicketResultResponseDto ticketWon = resultCheckerFacade.isSpecificTicketWon(hash);
        //then
        WinningTicketDto winningTicketDto = ticketWon.winningTicket();
        assertAll(
                () -> assertThat(ticketWon.status()).isEqualTo(ResultStatus.PRIZE_NOT_RECEIVED),
                () -> assertThat(winningTicketDto.hash()).isEqualTo(hash),
                () -> assertThat(winningTicketDto.userNumbers())
                        .hasSize(6)
                        .containsAll(numbers));
    }

    @Test
    void should_return_ticket_with_status_PRIZE_RECEIVED_for_given_hash(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers, Instant.now(), 1L, 5, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers, Instant.now(), 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        TicketResultResponseDto ticketWon = resultCheckerFacade.isSpecificTicketWon(hash);
        //then
        WinningTicketDto winningTicketDto = ticketWon.winningTicket();
        assertAll(
                () -> assertThat(ticketWon.status()).isEqualTo(ResultStatus.PRIZE_RECEIVED),
                () -> assertThat(winningTicketDto.hash()).isEqualTo(hash),
                () -> assertThat(winningTicketDto.userNumbers())
                        .hasSize(6)
                        .containsAll(numbers));
    }

    @Test
    void should_return_not_found_status_when_winning_ticket_does_not_exists_in_winning_tickets_db(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        String hash = "hash1";
        //when
        TicketResultResponseDto ticketWon = resultCheckerFacade.isSpecificTicketWon(hash);
        //then
        assertThat(ticketWon.status()).isEqualTo(ResultStatus.NOT_FOUND);
    }

    @Test
    void should_return_true_with_six_matching_numbers_for_given_draw_date_and_numbers(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(1, 2, 3, 4, 5, 6);
        when(numbersGeneratorFacade.getWinningNumbersForDate(any())).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(7, 12, 43, 4, 5, 6);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers, drawDate, 1L, 6, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers2, drawDate, 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        BasicTicketInfoResponseDto ticketResult = resultCheckerFacade.checkGivenNumbersForLottery(numbers, drawDate);
        //then
        assertAll(
                () -> assertThat(ticketResult.won()).isTrue(),
                () -> assertThat(ticketResult.matchingNumbersAmount()).isEqualTo(6)
        );
    }

    @Test
    void should_return_true_with_four_matching_numbers_for_given_draw_date_and_numbers(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(1, 2, 3, 4, 5, 6);
        when(numbersGeneratorFacade.getWinningNumbersForDate(any())).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers1 = List.of(7, 12, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(11, 22, 33,44, 45, 46);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers1, drawDate, 1L, 6, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers2, drawDate, 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        BasicTicketInfoResponseDto ticketResult = resultCheckerFacade.checkGivenNumbersForLottery(numbers1, drawDate);
        //then
        assertAll(
                () -> assertThat(ticketResult.won()).isTrue(),
                () -> assertThat(ticketResult.matchingNumbersAmount()).isEqualTo(4)
        );
    }

    @Test
    void should_return_false_if_less_than_four_matching_numbers_for_given_draw_date_and_numbers(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(1, 2, 3, 4, 5, 6);
        when(numbersGeneratorFacade.getWinningNumbersForDate(any())).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers1 = List.of(7, 12, 33, 4, 5, 6);
        List<Integer> numbers2 = List.of(11, 22, 33,44, 45, 46);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers1, drawDate, 1L, 6, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers2, drawDate, 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        BasicTicketInfoResponseDto ticketResult = resultCheckerFacade.checkGivenNumbersForLottery(numbers1, drawDate);
        //then
        assertAll(
                () -> assertThat(ticketResult.won()).isFalse(),
                () -> assertThat(ticketResult.matchingNumbersAmount()).isEqualTo(3)
        );
    }

    @Test
    void should_return_true_with_six_matching_numbers_for_given_lottery_number_and_numbers(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(1, 2, 3, 4, 5, 6);
        when(numbersGeneratorFacade.getWinningNumbersForLotteryNumber(any())).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(7, 12, 43, 4, 5, 6);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers, Instant.now(), 1L, 6, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers2, Instant.now(), 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        BasicTicketInfoResponseDto ticketResult = resultCheckerFacade.checkGivenNumbersForLottery(numbers, 1L);
        //then
        assertAll(
                () -> assertThat(ticketResult.won()).isTrue(),
                () -> assertThat(ticketResult.matchingNumbersAmount()).isEqualTo(6)
        );
    }

    @Test
    void should_return_true_with_four_matching_numbers_for_given_lottery_number_and_numbers(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(1, 2, 3, 4, 5, 6);
        when(numbersGeneratorFacade.getWinningNumbersForLotteryNumber(any())).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers1 = List.of(7, 12, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(11, 22, 33,44, 45, 46);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers1, Instant.now(), 1L, 6, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers2, Instant.now(), 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        BasicTicketInfoResponseDto ticketResult = resultCheckerFacade.checkGivenNumbersForLottery(numbers1, 1L);
        //then
        assertAll(
                () -> assertThat(ticketResult.won()).isTrue(),
                () -> assertThat(ticketResult.matchingNumbersAmount()).isEqualTo(4)
        );
    }

    @Test
    void should_return_false_if_less_than_four_matching_numbers_for_given_lottery_number_and_numbers(){
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade, ticketRepository);
        LocalDateTime localDateTime = of(2024, Month.JUNE, 14, 20, 0);
        Instant drawDate = localDateTime.toInstant(ZoneOffset.UTC);
        List<Integer> winningNumbersList = List.of(1, 2, 3, 4, 5, 6);
        when(numbersGeneratorFacade.getWinningNumbersForLotteryNumber(any())).thenReturn(
                new WinningNumbersDto(winningNumbersList, drawDate, 2L));
        String hash = "hash1";
        String hash2 = "hash2";
        List<Integer> numbers1 = List.of(7, 12, 33, 4, 5, 6);
        List<Integer> numbers2 = List.of(11, 22, 33,44, 45, 46);
        WinningTicket winningTicket1 = new WinningTicket(hash, numbers1, Instant.now(), 1L, 6, true);
        WinningTicket winningTicket2 = new WinningTicket(hash2, numbers2, Instant.now(), 1L, 4, false);
        ticketRepository.saveAll(List.of(winningTicket1, winningTicket2));
        //when
        BasicTicketInfoResponseDto ticketResult = resultCheckerFacade.checkGivenNumbersForLottery(numbers1, 1L);
        //then
        assertAll(
                () -> assertThat(ticketResult.won()).isFalse(),
                () -> assertThat(ticketResult.matchingNumbersAmount()).isEqualTo(3)
        );
    }
}