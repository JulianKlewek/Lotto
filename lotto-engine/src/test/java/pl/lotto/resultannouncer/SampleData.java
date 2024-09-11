package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.TicketDetails;
import pl.lotto.resultchecker.dto.WinningTicketPayload;

import java.time.Instant;
import java.util.List;

interface SampleData {

    String winReceivedMsg = "Congratulations you have won!";
    String winNotReceivedMsg = "Congratulations you have won, but already received reward.";
    String loseMsg = "Unfortunately you didn't won.";
    WinningTicketPayload winningTicket = new WinningTicketPayload("hash1", List.of(1, 2, 3, 4, 5, 6),
            Instant.parse("2024-06-14T20:00:00.00Z"), 1L, 5);
    WinningTicketPayload losingTicket = new WinningTicketPayload("hash1", null, null, null, 0);
    TicketDetails announcedWinningTicket = new TicketDetails("hash1", List.of(1, 2, 3, 4, 5, 6),
            Instant.parse("2024-06-14T20:00:00.00Z"), 1L, 5);
    TicketDetails announcedLosingTicket = new TicketDetails("hash1", null, null, null, 0);
}
