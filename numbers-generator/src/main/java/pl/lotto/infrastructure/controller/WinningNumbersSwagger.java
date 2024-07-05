package pl.lotto.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pl.lotto.infrastructure.controller.error.ApiError;
import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import java.time.Instant;

@Tag(name = "Winning Numbers RestController", description = "Microservice responsible for winning numbers")
interface WinningNumbersSwagger {

    @Operation(summary = "Get numbers for date", description = "Returns a winning numbers details for given drawDate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned winning numbers",
                    content = {@Content(schema = @Schema(implementation = WinningNumbersResponseDto.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Winning numbers are not generated for given date",
                    content = {@Content(schema = @Schema(implementation = ApiError.class),
                            mediaType = "application/json")})
    })
    ResponseEntity<WinningNumbersResponseDto> getWinningNumbersForDate(
            @PathVariable("drawDate") @Parameter(name = "drawDate", example = "2024-06-07T20:00:00Z",
                    description = "Draw date provided in Instant.class format", required = true) Instant drawDate);
}
