package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class DrawNumberGenerator {

    private final WinningNumbersRepository numbersRepository;

    public Long generateDrawNumber() {
        WinningNumbersDetails winningNumbersDetails = numbersRepository.findTopByOrderByLotteryNumberDesc()
                .orElse(new WinningNumbersDetails(null, null, 0L));
        return winningNumbersDetails.lotteryNumber + 1;
    }
}
