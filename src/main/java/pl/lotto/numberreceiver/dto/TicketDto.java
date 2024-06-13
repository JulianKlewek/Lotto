package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record TicketDto(
        String hash,
        Set<Integer> numbers,
        Instant drawDate,
        String message
) {
}
