package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.BasicTicketInfoResponseDto;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.util.List;

public interface ResultCheckerFacade {
    WinningTicketsDto checkAllWinningTicketsForGivenDrawDate(Instant drawDate);

    BasicTicketInfoResponseDto checkGivenNumbersForLottery(List<Integer> numbers, Instant drawDate);

    BasicTicketInfoResponseDto checkGivenNumbersForLottery(List<Integer> numbers, Long lotteryId);

    TicketResultResponseDto isSpecificTicketWon(String ticketHash);

    boolean isSystemGeneratedResults(Instant drawDate);
}