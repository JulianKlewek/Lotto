package pl.lotto.resultannouncer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record WinningResultsResponse(
        @Schema(description = "List containing winning numbers", example = "[1,2,3,4,5,6]")
        List<Integer> numbers,
        @Schema(description = "Date of draw", example = "2024-06-07T20:00:00Z")
        Instant drawDate,
        @Schema(description = "Lottery id", example = "1")
        Long lotteryNumber) {
}
