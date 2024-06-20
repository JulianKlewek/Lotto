package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultannouncer.dto.AnnouncerTicketDto;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;

class AnnouncerResponseMapper {

    private AnnouncerResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnouncerResponseDto toDto(TicketResultResponseDto ticket, String resultMessage) {
        AnnouncerTicketDto announcerTicketDto = AnnouncerTicketMapper.toDto(ticket.winningTicket());
        return AnnouncerResponseDto.builder()
                .message(resultMessage)
                .ticket(announcerTicketDto)
                .build();
    }
}
