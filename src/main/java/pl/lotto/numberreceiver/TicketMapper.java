package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

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

    public static List<TicketDto> toDtoList(List<Ticket> tickets){
        return tickets.stream()
                .map(TicketMapper::toDto)
                .toList();
    }
}
