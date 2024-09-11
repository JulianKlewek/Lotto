package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketPayload;

import java.util.List;

import static pl.lotto.numberreceiver.dto.TicketPayload.*;

class TicketMapper {

    private TicketMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static TicketPayload toDto(pl.lotto.numberreceiver.Ticket ticket) {
        List<Integer> userNumbersList = List.copyOf(ticket.userNumbers);
        return builder()
                .hash(ticket.hash)
                .numbers(userNumbersList)
                .drawDate(ticket.drawDate)
                .build();
    }

    public static List<TicketPayload> toDtoList(List<pl.lotto.numberreceiver.Ticket> tickets) {
        return tickets.stream()
                .map(TicketMapper::toDto)
                .toList();
    }
}
