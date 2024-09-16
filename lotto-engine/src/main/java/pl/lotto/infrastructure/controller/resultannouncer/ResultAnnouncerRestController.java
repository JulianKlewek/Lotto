package pl.lotto.infrastructure.controller.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultResponse;
import pl.lotto.resultannouncer.dto.WinningResultsResponse;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("result")
public class ResultAnnouncerRestController implements ResultAnnouncerApi {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping("/get-result/{ticketId}")
    public ResponseEntity<ResultResponse> getTicketResult(@PathVariable("ticketId") String ticketId) {
        ResultResponse results = resultAnnouncerFacade.findResultsForId(ticketId);
        return ResponseEntity
                .ok(results);
    }

    @GetMapping("/get-results/{drawDate}")
    public ResponseEntity<WinningResultsResponse> lotteryResultsForDate(@PathVariable("drawDate") Instant drawDate) {
        WinningResultsResponse response = resultAnnouncerFacade.getLotteryResultsForDate(drawDate);
        return ResponseEntity
                .ok(response);
    }
}
