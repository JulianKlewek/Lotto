package pl.lotto.infrastructure.winningnumbersservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.time.Instant;

@AllArgsConstructor
@Service
public class WinningNumbersGeneratorPortHttpService implements WinningNumbersPort {

    private final WinningNumbersWebClientCallGenerator winningNumbersWebClientCallGenerator;

    @Override
    public WinningNumbersResponse getWinningNumbersForDate(Instant drawDate) {
        return winningNumbersWebClientCallGenerator.callForWinningNumbersWithDrawDate(drawDate);
    }

    @Override
    public WinningNumbersResponse getWinningNumbersForLotteryNumber(Long lotteryId) {
        return null;
    }

}
