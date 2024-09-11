package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerResultResponse;
import pl.lotto.resultannouncer.dto.AnnouncerTicket;
import pl.lotto.resultchecker.dto.TicketResultResponse;

class AnnouncerResponseMapper {

    private AnnouncerResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnouncerResultResponse toDto(TicketResultResponse ticket, String resultMessage) {
        AnnouncerTicket announcerTicket = AnnouncerTicketMapper.toDto(ticket.winningTicket());
        return AnnouncerResultResponse.builder()
                .message(resultMessage)
                .ticket(announcerTicket)
                .build();
    }
}
