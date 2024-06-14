package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Set;

@Builder
public record TicketDto(
        String hash,
        Set<Integer> numbers,
        ZonedDateTime drawDate,
        String message
) {
}
