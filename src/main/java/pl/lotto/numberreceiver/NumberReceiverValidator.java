package pl.lotto.numberreceiver;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static pl.lotto.numberreceiver.NumberValidationResult.failure;
import static pl.lotto.numberreceiver.NumberValidationResult.success;
import static pl.lotto.numberreceiver.ValidationError.*;

class NumberReceiverValidator {
    public final int MINIMUM_NUMBER = 1;
    public final int MAXIMUM_NUMBER = 50;

    List<String> errorsList = new LinkedList<>();

    public NumberValidationResult validate(Set<Integer> numbersFromUser) {
        if (isUserProvidedMoreThanSixNumbers(numbersFromUser)) {
            errorsList.add(MORE_THAN_SIX_NUMBERS.errorMessage);
        }
        if (isUserProvidedLessThanSixNumbers(numbersFromUser)) {
            errorsList.add(LESS_THAN_SIX_NUMBERS.errorMessage);
        }
        if (!isNumbersInRange(numbersFromUser)) {
            errorsList.add(OUT_OF_RANGE.errorMessage);
        }
        if (errorsList.isEmpty()) {
            return success();
        }
        return failure(errorsList);
    }

    private static boolean isUserProvidedMoreThanSixNumbers(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() > 6;
    }

    private static boolean isUserProvidedLessThanSixNumbers(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() < 6;
    }

    private boolean isNumbersInRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .allMatch(this::isInRange);
    }

    private boolean isInRange(Integer integer) {
        return (integer >= MINIMUM_NUMBER && integer <= MAXIMUM_NUMBER);
    }


}
