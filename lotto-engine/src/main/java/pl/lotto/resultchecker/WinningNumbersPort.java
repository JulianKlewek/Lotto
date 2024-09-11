package pl.lotto.resultchecker;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;

import java.time.Instant;

public interface WinningNumbersPort {

    WinningNumbersResponse getWinningNumbersForDate(Instant drawDate);

    WinningNumbersResponse getWinningNumbersForLotteryNumber(Long lotteryId);

}
