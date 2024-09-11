package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.WinningResultsResponse;
import pl.lotto.resultchecker.dto.WinningNumbersResults;

public class WinningNumbersAnnouncerMapper {

    private WinningNumbersAnnouncerMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningResultsResponse resultsToResponse(WinningNumbersResults results) {
        return WinningResultsResponse.builder()
                .numbers(results.numbers())
                .drawDate(results.drawDate())
                .lotteryNumber(results.lotteryNumber())
                .build();
    }
}
