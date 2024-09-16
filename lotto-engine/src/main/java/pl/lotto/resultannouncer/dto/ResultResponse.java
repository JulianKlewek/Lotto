package pl.lotto.resultannouncer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ResultResponse(
        @Schema(description = "Ticket object")
        TicketDetails ticket,
        @Schema(description = "Result message", example = "Congratulations you have won, but already received reward.")
        String message) {

}
