package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WinningTicketsDto(List<WinningTicketDto> winningTickets) {
}
