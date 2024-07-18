package pl.lotto.infrastructure.controller.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

record ApiErrorResponse(

        @Schema(description = "Error message", example = "\"VALIDATION_FAILED\"")
        String message,
        @Schema(description = "Errors map", example = "{\"password\": \"INSUFFICIENT_DIGIT\"}")
        Map<String, String> errors) {
}
