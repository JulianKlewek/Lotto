package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import static pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto.*;

class WinningNumbersMapper {

    private WinningNumbersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningNumbersResponseDto toDto(WinningNumbersDetails winningNumbersDetails) {
        return builder()
                .numbers(winningNumbersDetails.numbers)
                .lotteryNumber(winningNumbersDetails.lotteryNumber)
                .drawDate(winningNumbersDetails.drawDate)
                .build();
    }

}
