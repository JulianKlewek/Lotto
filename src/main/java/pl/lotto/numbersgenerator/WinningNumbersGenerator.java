package pl.lotto.numbersgenerator;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

class WinningNumbersGenerator {

    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 50;
    private static final Random RANDOM = new Random();

    public Set<Integer> generateSixNumbersInGivenRange() {
        return RANDOM
                .ints(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .distinct()
                .boxed()
                .limit(6)
                .collect(Collectors.toSet());
    }

    public Long generateDrawNumber(WinningNumbersRepository numbersRepository) {
        Long highestDrawNumber = numbersRepository.findFirstByLotteryNumberOrderByLotteryNumberDesc();
        return highestDrawNumber+1;
    }
}
