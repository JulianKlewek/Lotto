package pl.lotto.resultchecker;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;

import java.util.List;

class NobodyWonTicketGenerator {

    public WinningTicket generateDataNobodyWon(WinningNumbersResponseDto winningNumber) {
        String hash = "hash" + winningNumber.lotteryNumber();
        return WinningTicket.builder()
                .userNumbers(inverseWinningNumbers(winningNumber.numbers()))
                .hash(hash)
                .collectedReward(false)
                .amountOfCorrectNumbers(0)
                .lotteryNumber(winningNumber.lotteryNumber())
                .drawDate(winningNumber.drawDate())
                .build();
    }

    private List<Integer> inverseWinningNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(numb -> numb * -1)
                .toList();
    }
}
