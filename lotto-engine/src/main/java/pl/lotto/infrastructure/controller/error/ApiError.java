package pl.lotto.infrastructure.controller.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record ApiError(
        @Schema(description = "List of errors", example = "[\"Winning numbers for given data not found. \"]")
        List<String> errors) {
}
