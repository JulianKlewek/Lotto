package pl.lotto.resultchecker;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultchecker.dto.WinningNumbersResults;

public class WinningNumbersResultMapper {

    private WinningNumbersResultMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningNumbersResults responseToDto(WinningNumbersResponse response) {
        return WinningNumbersResults.builder()
                .numbers(response.numbers())
                .drawDate(response.drawDate())
                .lotteryNumber(response.lotteryNumber())
                .build();
    }
}
