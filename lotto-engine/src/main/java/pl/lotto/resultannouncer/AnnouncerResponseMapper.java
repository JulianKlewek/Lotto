package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultResponse;
import pl.lotto.resultannouncer.dto.TicketDetails;
import pl.lotto.resultchecker.dto.TicketResultResponse;

class AnnouncerResponseMapper {

    private AnnouncerResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultResponse toDto(TicketResultResponse ticket, String resultMessage) {
        TicketDetails ticketDetails = AnnouncerTicketMapper.toDto(ticket.winningTicket());
        return ResultResponse.builder()
                .message(resultMessage)
                .ticket(ticketDetails)
                .build();
    }
}
