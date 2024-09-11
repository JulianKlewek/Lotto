package pl.lotto.resultannouncer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AnnouncerResultResponse(
        @Schema(description = "Ticket object")
        AnnouncerTicket ticket,
        @Schema(description = "Result message", example = "Congratulations you have won, but already received reward.")
        String message) {

}
