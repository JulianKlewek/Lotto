package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record TicketDto(
        String hash,
        List<Integer> numbers,
        Instant drawDate,
        String message
) {
}
