package pl.lotto.infrastructure.winningnumbersservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.lotto.infrastructure.controller.error.WinningNumbersGeneratorException;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Log4j2
@Component
public class WinningNumbersWebClientCallGenerator {

    private final WebClient.Builder webClient;
    private String numbersGeneratorServiceUrl;

    public WinningNumbersWebClientCallGenerator(WebClient.Builder webClient, @Value("${lotto.number-generator.service.url}") String numbersGeneratorServiceUrl) {
        this.webClient = webClient;
        this.numbersGeneratorServiceUrl = numbersGeneratorServiceUrl;
    }

    public WinningNumbersResponseDto callForWinningNumbersWithDrawDate(Instant drawDate) {
        log.debug("Processing request numbers generator service to fetch winning numbers for date [{}]", drawDate);
        String uri = "/winning-numbers/{drawDate}";
        log.debug("Calling Numbers-generator-service for url: [{}{}]", numbersGeneratorServiceUrl, uri);
        return webClient
                .baseUrl(numbersGeneratorServiceUrl).build()
                .get()
                .uri(uri, drawDate)
                .retrieve()
                .bodyToMono(WinningNumbersResponseDto.class)
                .onErrorResume(e ->
                        Mono.error(new WinningNumbersGeneratorException("Winning numbers for given data not found. ")))
                .block();
    }
}
