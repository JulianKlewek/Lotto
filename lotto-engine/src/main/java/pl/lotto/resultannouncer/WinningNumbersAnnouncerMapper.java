package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponse;
import pl.lotto.resultchecker.dto.WinningNumbersResults;

public class WinningNumbersAnnouncerMapper {

    private WinningNumbersAnnouncerMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnouncerWinningResultsResponse resultsToResponse(WinningNumbersResults results) {
        return AnnouncerWinningResultsResponse.builder()
                .numbers(results.numbers())
                .drawDate(results.drawDate())
                .lotteryNumber(results.lotteryNumber())
                .build();
    }
}
