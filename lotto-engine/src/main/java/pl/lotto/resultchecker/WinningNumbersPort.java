package pl.lotto.resultchecker;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;

import java.time.Instant;

public interface WinningNumbersPort {

    WinningNumbersResponseDto getWinningNumbersForDate(Instant drawDate);

    WinningNumbersResponseDto getWinningNumbersForLotteryNumber(Long lotteryId);

    Instant getLatestDrawDateWithGeneratedNumbers();
}
