package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerTicket;
import pl.lotto.resultchecker.dto.WinningTicketPayload;

class AnnouncerTicketMapper {

    private AnnouncerTicketMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnouncerTicket toDto(WinningTicketPayload ticket) {
        return AnnouncerTicket.builder()
                .hash(ticket.hash())
                .lotteryNumber(ticket.lotteryNumber())
                .drawDate(ticket.drawDate())
                .userNumbers(ticket.userNumbers())
                .amountOfCorrectNumbers(ticket.amountOfCorrectNumbers())
                .build();
    }
}
