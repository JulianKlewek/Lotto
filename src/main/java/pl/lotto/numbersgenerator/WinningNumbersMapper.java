package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

class WinningNumbersMapper {

    private WinningNumbersMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static WinningNumbersDto toDto(WinningNumbersDetails winningNumbersDetails) {
        return WinningNumbersDto.builder()
                .numbers(winningNumbersDetails.numbers)
                .lotteryNumber(winningNumbersDetails.lotteryNumber)
                .generatedTime(winningNumbersDetails.generatedTime)
                .build();
    }

}
