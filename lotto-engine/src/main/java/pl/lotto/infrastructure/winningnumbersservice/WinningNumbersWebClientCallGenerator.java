package pl.lotto.infrastructure.winningnumbersservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.lotto.infrastructure.controller.error.WinningNumbersGeneratorException;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RequiredArgsConstructor
@Component
@Log4j2
public class WinningNumbersWebClientCallGenerator {

    private final WebClient.Builder webClient;

    @Value("${lotto.number-generator.service.url}")
    private String NUMBERS_GENERATOR_SERVICE_URL;

    public WinningNumbersResponseDto callForWinningNumbersWithDrawDate(Instant drawDate) {
        return webClient
                .baseUrl(NUMBERS_GENERATOR_SERVICE_URL).build()
                .get()
                .uri("/winning-numbers/{drawDate}", drawDate)
                .retrieve()
                .bodyToMono(WinningNumbersResponseDto.class)
                .onErrorResume(e ->
                        Mono.error(new WinningNumbersGeneratorException("Winning numbers for given data not found. ")))
                .block();
    }
}
