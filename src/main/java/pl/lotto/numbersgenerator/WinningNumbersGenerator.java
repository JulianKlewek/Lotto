package pl.lotto.numbersgenerator;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


class WinningNumbersGenerator implements WinningNumbersGenerable {

    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 50;
    private static final Random RANDOM = new Random();

    @Override
    public Set<Integer> generateSixNumbersInGivenRange() {
        return RANDOM
                .ints(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .distinct()
                .boxed()
                .limit(6)
                .collect(Collectors.toSet());
    }

}
