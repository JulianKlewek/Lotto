package pl.lotto.infrastructure.controller.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.AnnouncerResultResponse;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponse;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("result")
public class ResultAnnouncerRestController implements ResultAnnouncerApi {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping("/get-result/{ticketId}")
    public ResponseEntity<AnnouncerResultResponse> getTicketResult(@PathVariable("ticketId") String ticketId) {
        AnnouncerResultResponse results = resultAnnouncerFacade.findResultsForId(ticketId);
        return ResponseEntity
                .ok(results);
    }

    @GetMapping("/get-results/{drawDate}")
    public ResponseEntity<AnnouncerWinningResultsResponse> lotteryResultsForDate(@PathVariable("drawDate") Instant drawDate) {
        AnnouncerWinningResultsResponse response = resultAnnouncerFacade.getLotteryResultsForDate(drawDate);
        return ResponseEntity
                .ok(response);
    }
}
