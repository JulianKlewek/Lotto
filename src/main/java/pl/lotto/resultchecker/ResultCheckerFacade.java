package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.util.List;

public interface ResultCheckerFacade {
    WinningTicketsDto checkAllWinningTicketsForGivenDrawDate(Instant drawDate);

    TicketResultResponseDto isTicketWon(List<Integer> numbers, Instant drawDate);
    TicketResultResponseDto isTicketWon(List<Integer> numbers, Long lotteryId);
    TicketResultResponseDto isTicketWon(String ticketHash);
}