package pl.lotto.resultchecker.dto;

import lombok.Builder;
import pl.lotto.resultchecker.ResultStatus;

@Builder
public record TicketResultResponse(WinningTicketPayload winningTicket, ResultStatus status) {
}
