package pl.lotto.infrastructure.winningnumbersservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.time.Instant;

@AllArgsConstructor
@Service
public class WinningNumbersGeneratorPortHttpService implements WinningNumbersPort {

    private final WinningNumbersWebClientCallGenerator winningNumbersWebClientCallGenerator;

    @Override
    public WinningNumbersResponseDto getWinningNumbersForDate(Instant drawDate) {
        return winningNumbersWebClientCallGenerator.callForWinningNumbersWithDrawDate(drawDate);
    }

    @Override
    public WinningNumbersResponseDto getWinningNumbersForLotteryNumber(Long lotteryId) {
        return null;
    }

}
