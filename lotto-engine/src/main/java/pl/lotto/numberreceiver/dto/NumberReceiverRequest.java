package pl.lotto.numberreceiver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record NumberReceiverRequest(
        @Schema(name = "inputNumbers", description = "List of integers.", example = "[1,2,3,4,5,6]") List<Integer> inputNumbers) {
}
