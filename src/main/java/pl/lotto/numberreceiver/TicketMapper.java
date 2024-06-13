package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

import static pl.lotto.numberreceiver.dto.TicketDto.*;

class TicketMapper {

    private TicketMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static TicketDto toDto(Ticket ticket){
        return builder()
                .hash(ticket.hash)
                .numbers(ticket.userNumbers)
                .drawDate(ticket.drawDate)
                .build();
    }
}
