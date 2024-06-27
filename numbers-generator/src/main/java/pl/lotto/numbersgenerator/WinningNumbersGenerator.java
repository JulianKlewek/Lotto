package pl.lotto.numbersgenerator;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class WinningNumbersGenerator implements WinningNumbersGenerable {

    private final WinningNumbersPropertyConfigurable winningNumbersProperty;
    private static final Random RANDOM = new Random();

    @Override
    public Set<Integer> generateSixNumbersInGivenRange() {
        return RANDOM
                .ints(winningNumbersProperty.getMinimumNumber(), winningNumbersProperty.getMaximumNumber())
                .distinct()
                .boxed()
                .limit(winningNumbersProperty.getAmountOfNumbers())
                .collect(Collectors.toSet());
    }

}
