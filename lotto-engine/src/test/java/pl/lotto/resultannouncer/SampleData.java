package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;

import java.time.Instant;
import java.util.List;

interface SampleData {

    String winReceivedMsg = "Congratulations you have won!";
    String winNotReceivedMsg = "Congratulations you have won, but already received reward.";
    String loseMsg = "Unfortunately you didn't won.";
    WinningTicketDto winningTicket = new WinningTicketDto("hash1", List.of(1, 2, 3, 4, 5, 6),
            Instant.parse("2024-06-14T20:00:00.00Z"), 1L, 5);
    WinningTicketDto losingTicket = new WinningTicketDto("hash1", null, null, null, 0);
    AnnouncerTicketDto announcedWinningTicket = new AnnouncerTicketDto("hash1", List.of(1, 2, 3, 4, 5, 6),
            Instant.parse("2024-06-14T20:00:00.00Z"), 1L, 5);
    AnnouncerTicketDto announcedLosingTicket = new AnnouncerTicketDto("hash1", null, null, null, 0);
}
