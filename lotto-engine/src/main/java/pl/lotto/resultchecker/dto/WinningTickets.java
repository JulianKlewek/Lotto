package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WinningTickets(List<WinningTicketPayload> winningTickets) {
}
