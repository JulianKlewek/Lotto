package pl.lotto.numberreceiver.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record NumberReceiverResult(
        @Schema(example = "success")
        String status,
        @Schema(example = "[\"User provided more than six numbers\",\"User provided numbers out of range\"]",
                description = "List of errors. ")
        List<String> errorsList,
        @Schema(description = "Ticket object")
        TicketPayload ticket) {
}
