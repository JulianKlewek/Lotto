package pl.lotto.resultannouncer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record AnnouncerTicketDto(
        @Schema(description = "unique id for ticket", example = "e58ed763-928c-4155-bee9-fdbaaadc15f3")
        String hash,
        @Schema(description = "List of numbers", example = "[1,2,3,4,5,6]")
        List<Integer> userNumbers,
        @Schema(description = "Date of draw for given ticket.", example = "2024-06-07T20:00:00Z")
        Instant drawDate,
        @Schema(description = "Lottery id", example = "1")
        Long lotteryNumber,
        @Schema(description = "Amount of winning numbers", example = "5")
        int amountOfCorrectNumbers) {
}
