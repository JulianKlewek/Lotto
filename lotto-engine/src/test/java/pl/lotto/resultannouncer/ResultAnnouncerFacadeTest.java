package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import pl.lotto.drawdategenerator.dto.DrawDate;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultannouncer.dto.ResultResponse;
import pl.lotto.resultchecker.ResultStatus;
import pl.lotto.resultchecker.dto.TicketResultResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest extends ResultAnnouncerFacadeTestConfig {

    @Test
    void should_return_ticket_with_message_win_reward_not_received() {
        //given
        String hash = "hash";
        when(resultCheckerFacade.isSpecificTicketWon(any())).thenReturn(new TicketResultResponse(
                winningTicket, ResultStatus.PRIZE_NOT_RECEIVED));
        //when
        ResultResponse resultsForId = resultAnnouncerFacade.findResultsForId(hash);
        //then
        assertAll(
                () -> assertThat(resultsForId.message()).isEqualTo(winNotReceivedMsg),
                () -> assertThat(resultsForId.ticket()).isEqualTo(announcedWinningTicket));
    }

    @Test
    void should_return_ticket_with_message_win_reward_received() {
        //given
        String hash = "hash1";
        when(resultCheckerFacade.isSpecificTicketWon(any())).thenReturn(
                new TicketResultResponse(winningTicket, ResultStatus.PRIZE_RECEIVED));
        //when
        ResultResponse resultsForId = resultAnnouncerFacade.findResultsForId(hash);
        //then
        assertAll(
                () -> assertThat(resultsForId.message()).isEqualTo(winReceivedMsg),
                () -> assertThat(resultsForId.ticket()).isEqualTo(announcedWinningTicket));
    }

    @Test
    void should_return_ticket_with_message_lose() {
        //given
        String hash = "hash1";
        when(resultCheckerFacade.isSpecificTicketWon(any())).thenReturn(
                new TicketResultResponse(losingTicket, ResultStatus.NOT_FOUND));
        //when
        ResultResponse resultsForId = resultAnnouncerFacade.findResultsForId(hash);
        //then
        assertAll(
                () -> assertThat(resultsForId.message()).isEqualTo(loseMsg),
                () -> assertThat(resultsForId.ticket()).isEqualTo(announcedLosingTicket));
    }

    @Test
    void should_return_latest_lottery_results() {
        //given
        Instant drawDate = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0).toInstant(ZoneOffset.UTC);
        Instant now = LocalDateTime.of(2024, Month.JUNE, 14, 21, 0).toInstant(ZoneOffset.UTC);
        when(drawDateGeneratorFacade.getLatestDrawDate(now)).thenReturn(
                new DrawDate(drawDate));
        when(winningNumbersPort.getLatestWinningNumbers()).thenReturn(
                new WinningNumbersResponse(List.of(1, 2, 3, 4, 5, 6), drawDate, 1L));
        //when
        WinningNumbersResponse lotteryResults = resultAnnouncerFacade.getLatestLotteryResults();
        //then
        assertAll(
                () -> assertThat(lotteryResults.numbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(lotteryResults.drawDate()).isEqualTo(drawDate),
                () -> assertThat(lotteryResults.lotteryNumber()).isEqualTo(1L));
    }
}
