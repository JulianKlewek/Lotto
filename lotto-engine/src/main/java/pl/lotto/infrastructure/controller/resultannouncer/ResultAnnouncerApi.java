package pl.lotto.infrastructure.controller.resultannouncer;

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
import pl.lotto.resultannouncer.dto.AnnouncerResultResponse;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponse;

import java.time.Instant;

@Tag(name = "Result announcer RestController", description = "Microservice responsible for announcing results")
interface ResultAnnouncerApi {

    @Operation(summary = "Returns result for given ticketId", description = "Returns a ticket with numbers given by user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned result for given ticketId",
                    content = {@Content(schema = @Schema(implementation = AnnouncerResultResponse.class),
                            mediaType = "application/json")})
    })
    ResponseEntity<AnnouncerResultResponse> getTicketResult(@PathVariable @Parameter(name = "ticketId",
            example = "e58ed763-928c-4155-bee9-fdbaaadc15f3", description = "ticket uniqueId in UUID format as String",
            required = true) String ticketId);

    @Operation(summary = "Returns results for drawDate", description = "Returns a detailed results for given draw date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned results for given draw date",
                    content = {@Content(schema = @Schema(implementation = AnnouncerWinningResultsResponse.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Response if could not find winning numbers for given date",
                    content = {@Content(schema = @Schema(implementation = ApiError.class),
                            mediaType = "application/json")})
    })
    ResponseEntity<AnnouncerWinningResultsResponse> lotteryResultsForDate(@PathVariable @Parameter(name = "drawDate",
            example = "2024-06-07T20:00:00Z", description = "Draw date provided in Instant.class format",
            required = true) Instant drawDate);
}
