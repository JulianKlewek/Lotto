package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class WinningNumbersGenerator {

    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 50;
    private static final Random RANDOM = new Random();

    private final WinningNumbersRepository numbersRepository;

    public Set<Integer> generateSixNumbersInGivenRange() {
        return RANDOM
                .ints(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .distinct()
                .boxed()
                .limit(6)
                .collect(Collectors.toSet());
    }

    public Long generateDrawNumber() {
        Long highestDrawNumber = numbersRepository.findFirstByLotteryNumberOrderByLotteryNumberDesc();
        return highestDrawNumber + 1;
    }
}
