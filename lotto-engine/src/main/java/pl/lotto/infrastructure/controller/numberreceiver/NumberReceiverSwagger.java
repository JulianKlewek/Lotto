package pl.lotto.infrastructure.controller.numberreceiver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lotto.numberreceiver.dto.NumberReceiverRequestDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

@Tag(name = "Number receiver RestController", description = "Microservice responsible for accepting numbers from players")
interface NumberReceiverSwagger {

    @Operation(summary = "Get user numbers input numbers", description = "Returns a ticket with numbers given by user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created ticket for given numbers",
                    content = {@Content(schema = @Schema(implementation = NumberReceiverResultDto.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Numbers given by user contains some error",
                    content = {@Content(schema = @Schema(implementation = NumberReceiverResultDto.class),
                            mediaType = "application/json")})
    })
    ResponseEntity<NumberReceiverResultDto> inputNumbers(@RequestBody NumberReceiverRequestDto request);

}
