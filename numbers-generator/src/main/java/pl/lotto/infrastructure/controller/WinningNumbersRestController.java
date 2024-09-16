package pl.lotto.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersResponse;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RestController
class WinningNumbersRestController implements WinningNumbersApi {

    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @GetMapping("/winning-numbers/{drawDate}")
    public ResponseEntity<WinningNumbersResponse> getWinningNumbersForDate(@PathVariable("drawDate") Instant drawDate) {
        WinningNumbersResponse response = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        return ResponseEntity
                .ok(response);
    }
}
