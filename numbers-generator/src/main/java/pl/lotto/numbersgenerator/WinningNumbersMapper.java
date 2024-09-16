package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersResponse;

import static pl.lotto.numbersgenerator.dto.WinningNumbersResponse.*;

class WinningNumbersMapper {

    private WinningNumbersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WinningNumbersResponse toDto(WinningNumbersDetails winningNumbersDetails) {
        return builder()
                .numbers(winningNumbersDetails.numbers)
                .lotteryNumber(winningNumbersDetails.lotteryNumber)
                .drawDate(winningNumbersDetails.drawDate)
                .build();
    }

}
