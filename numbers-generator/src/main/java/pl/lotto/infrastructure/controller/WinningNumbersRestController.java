package pl.lotto.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import java.time.Instant;

@AllArgsConstructor
@RestController
class WinningNumbersRestController {

    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @GetMapping("/winning-numbers/{drawDate}")
    public ResponseEntity<WinningNumbersResponseDto> getWinningNumbersForDate(@PathVariable("drawDate") Instant drawDate) {
        WinningNumbersResponseDto response = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        return ResponseEntity
                .ok(response);
    }
}
