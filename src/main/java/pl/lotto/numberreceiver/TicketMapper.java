package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

class TicketMapper {


    public static TicketDto toDto(Ticket ticket){
        return TicketDto.builder()
                .hash(ticket.hash)
                .numbers(ticket.userNumbers)
                .drawDate(ticket.drawDate)
                .build();
    }
}
