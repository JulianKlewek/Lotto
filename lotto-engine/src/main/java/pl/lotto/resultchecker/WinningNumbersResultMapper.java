package pl.lotto.resultchecker;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import pl.lotto.resultchecker.dto.WinningNumbersResultsDto;

public class WinningNumbersResultMapper {

    private WinningNumbersResultMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningNumbersResultsDto responseToDto(WinningNumbersResponseDto response) {
        return WinningNumbersResultsDto.builder()
                .numbers(response.numbers())
                .drawDate(response.drawDate())
                .lotteryNumber(response.lotteryNumber())
                .build();
    }
}
