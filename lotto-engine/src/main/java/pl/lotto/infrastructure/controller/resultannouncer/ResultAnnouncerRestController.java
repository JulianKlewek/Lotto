package pl.lotto.infrastructure.controller.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponseDto;

import java.time.Instant;

@RestController
@AllArgsConstructor
public class ResultAnnouncerRestController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping("/get-result/{ticketId}")
    public ResponseEntity<AnnouncerResponseDto> getTicketResult(@PathVariable String ticketId) {
        AnnouncerResponseDto results = resultAnnouncerFacade.findResultsForId(ticketId);
        return ResponseEntity
                .ok(results);
    }

    @GetMapping("/get-results/{drawDate}")
    public ResponseEntity<AnnouncerWinningResultsResponseDto> lotteryResultsForDate(@PathVariable Instant drawDate) {
        AnnouncerWinningResultsResponseDto response = resultAnnouncerFacade.getLotteryResultsForDate(drawDate);
        return ResponseEntity
                .ok(response);
    }
}
