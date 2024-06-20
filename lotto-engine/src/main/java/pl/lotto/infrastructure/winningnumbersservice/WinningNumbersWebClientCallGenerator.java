package pl.lotto.infrastructure.winningnumbersservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;

import java.time.Instant;

@AllArgsConstructor
@Component
public class WinningNumbersWebClientCallGenerator {

    private final WebClient webClient;

    public WinningNumbersResponseDto callForWinningNumbersWithDrawDate(Instant drawDate) {
        return webClient.get()
                .uri("winning-numbers/{drawDate}", drawDate)
                .retrieve()
                .bodyToMono(WinningNumbersResponseDto.class)
                .block();
    }
}
