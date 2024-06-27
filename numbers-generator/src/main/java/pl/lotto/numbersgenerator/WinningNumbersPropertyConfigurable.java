package pl.lotto.numbersgenerator;

public interface WinningNumbersPropertyConfigurable {

    int getMinimumNumber();

    void setMinimumNumber(int minimumNumber);

    int getMaximumNumber();

    void setMaximumNumber(int maximumNumber);

    int getAmountOfNumbers();

    void setAmountOfNumbers(int amountOfNumbers);
}
