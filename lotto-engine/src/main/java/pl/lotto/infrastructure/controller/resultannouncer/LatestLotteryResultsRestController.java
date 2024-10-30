package pl.lotto.infrastructure.controller.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;

@AllArgsConstructor
@RestController
@RequestMapping("results/latest")
public class LatestLotteryResultsRestController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping
    public ResponseEntity<WinningNumbersResponse> getLatestResults() {
        WinningNumbersResponse response = resultAnnouncerFacade.getLatestLotteryResults();
        return ResponseEntity
                .ok(response);
    }
}
