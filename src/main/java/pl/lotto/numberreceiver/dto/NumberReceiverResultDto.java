package pl.lotto.numberreceiver.dto;

import java.util.List;

public record NumberReceiverResultDto(
        String status,
        List<String> errorsList,
        TicketDto ticket) {
}
