package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
public record TicketDto(
        String hash,
        Set<Integer> numbers,
        LocalDateTime drawDate,
        String message
        ) {
}
