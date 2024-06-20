package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponseDto;
import pl.lotto.resultchecker.dto.WinningNumbersResultsDto;

public class WinningNumbersAnnouncerMapper {

    private WinningNumbersAnnouncerMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnouncerWinningResultsResponseDto resultsToResponse(WinningNumbersResultsDto results) {
        return AnnouncerWinningResultsResponseDto.builder()
                .numbers(results.numbers())
                .drawDate(results.drawDate())
                .lotteryNumber(results.lotteryNumber())
                .build();
    }
}
