package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.TicketDetails;
import pl.lotto.resultchecker.dto.WinningTicketPayload;

class AnnouncerTicketMapper {

    private AnnouncerTicketMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static TicketDetails toDto(WinningTicketPayload ticket) {
        return TicketDetails.builder()
                .hash(ticket.hash())
                .lotteryNumber(ticket.lotteryNumber())
                .drawDate(ticket.drawDate())
                .userNumbers(ticket.userNumbers())
                .amountOfCorrectNumbers(ticket.amountOfCorrectNumbers())
                .build();
    }
}
