package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserTickets(List<TicketPayload> tickets) {
}
