package pl.lotto.infrastructure.winningnumbersservice;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.lotto.infrastructure.controller.error.WinningNumbersGeneratorException;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import reactor.core.publisher.Mono;

import java.time.Instant;

@AllArgsConstructor
@Component
@Log4j2
public class WinningNumbersWebClientCallGenerator {

    private final WebClient.Builder webClient;

    public WinningNumbersResponseDto callForWinningNumbersWithDrawDate(Instant drawDate) {
        return webClient
                .baseUrl("http://numbersgeneratorservice/").build()
                .get()
                .uri("winning-numbers/{drawDate}", drawDate)
                .retrieve()
                .bodyToMono(WinningNumbersResponseDto.class)
                .onErrorResume(e ->
                        Mono.error(new WinningNumbersGeneratorException("Winning numbers for given data not found. ")))
                .block();
    }
}
