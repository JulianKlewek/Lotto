package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.BasicTicketInfoResponse;
import pl.lotto.resultchecker.dto.TicketResultResponse;
import pl.lotto.resultchecker.dto.WinningNumbersResults;
import pl.lotto.resultchecker.dto.WinningTickets;

import java.time.Instant;
import java.util.List;

public interface ResultCheckerFacade {
    WinningTickets checkAllWinningTicketsForGivenDrawDate(Instant drawDate);

    BasicTicketInfoResponse checkGivenNumbersForLottery(List<Integer> numbers, Instant drawDate);

    BasicTicketInfoResponse checkGivenNumbersForLottery(List<Integer> numbers, Long lotteryId);

    TicketResultResponse isSpecificTicketWon(String ticketHash);

    boolean isSystemGeneratedResults(Instant drawDate);

    WinningNumbersResults findWinningNumbersForLottery(Instant drawDate);
}