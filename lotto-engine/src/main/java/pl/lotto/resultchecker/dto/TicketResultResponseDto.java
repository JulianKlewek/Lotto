package pl.lotto.resultchecker.dto;

import lombok.Builder;
import pl.lotto.resultchecker.ResultStatus;

@Builder
public record TicketResultResponseDto(WinningTicketDto winningTicket, ResultStatus status) {
}
