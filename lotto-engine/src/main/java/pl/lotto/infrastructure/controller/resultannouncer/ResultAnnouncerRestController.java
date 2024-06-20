package pl.lotto.infrastructure.controller.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;

@RestController
@AllArgsConstructor
public class ResultAnnouncerRestController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping("/getResult/{ticketId}")
    public ResponseEntity<AnnouncerResponseDto> getTicketResult(@PathVariable String ticketId) {
        AnnouncerResponseDto results = resultAnnouncerFacade.findResultsForId(ticketId);
        return ResponseEntity
                .ok(results);
    }
}
