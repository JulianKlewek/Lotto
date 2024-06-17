package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.ResultCheckerFacadeImpl;
import pl.lotto.resultchecker.ResultStatus;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest extends ResultAnnouncerFacadeTestConfig {

    @Test
    void should_return_ticket_with_message_win_reward_not_received() {
        //given
        String hash = "hash";
        ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacadeImpl.class);
        ResultAnnouncerConfigurable resultAnnouncerConfigurable = new ResultAnnouncerPropertyConfigTest(
                winReceivedMsg, winNotReceivedMsg, loseMsg);
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().createResultAnnouncerFacadeForTests(
                resultCheckerFacade, resultAnnouncerConfigurable);
        when(resultCheckerFacade.isSpecificTicketWon(any())).thenReturn(new TicketResultResponseDto(
                winningTicket, ResultStatus.PRIZE_NOT_RECEIVED));
        //when
        AnnouncerResponseDto resultsForId = resultAnnouncerFacade.findResultsForId(hash);
        //then
        assertAll(
                () -> assertThat(resultsForId.message()).isEqualTo(winNotReceivedMsg),
                () -> assertThat(resultsForId.ticket()).isEqualTo(announcedWinningTicket));
    }

    @Test
    void should_return_ticket_with_message_win_reward_received() {
        //given
        String hash = "hash1";
        ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacadeImpl.class);
        ResultAnnouncerConfigurable resultAnnouncerConfigurable = new ResultAnnouncerPropertyConfigTest(
                winReceivedMsg, winNotReceivedMsg, loseMsg);
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().createResultAnnouncerFacadeForTests(
                resultCheckerFacade, resultAnnouncerConfigurable);
        when(resultCheckerFacade.isSpecificTicketWon(any())).thenReturn(
                new TicketResultResponseDto(winningTicket, ResultStatus.PRIZE_RECEIVED));
        //when
        AnnouncerResponseDto resultsForId = resultAnnouncerFacade.findResultsForId(hash);
        //then
        assertAll(
                () -> assertThat(resultsForId.message()).isEqualTo(winReceivedMsg),
                () -> assertThat(resultsForId.ticket()).isEqualTo(announcedWinningTicket));
    }

    @Test
    void should_return_ticket_with_message_lose() {
        //given
        String hash = "hash1";
        ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacadeImpl.class);
        ResultAnnouncerConfigurable resultAnnouncerConfigurable = new ResultAnnouncerPropertyConfigTest(
                winReceivedMsg, winNotReceivedMsg, loseMsg);
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().createResultAnnouncerFacadeForTests(
                resultCheckerFacade, resultAnnouncerConfigurable);
        when(resultCheckerFacade.isSpecificTicketWon(any())).thenReturn(
                new TicketResultResponseDto(losingTicket, ResultStatus.NOT_FOUND));
        //when
        AnnouncerResponseDto resultsForId = resultAnnouncerFacade.findResultsForId(hash);
        //then
        assertAll(
                () -> assertThat(resultsForId.message()).isEqualTo(loseMsg),
                () -> assertThat(resultsForId.ticket()).isEqualTo(announcedLosingTicket));
    }
}
