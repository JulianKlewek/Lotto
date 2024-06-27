package pl.lotto.numbersgenerator;

public class WinningNumbersPropertyTestConfig implements WinningNumbersPropertyConfigurable {

    private int minimumNumber;
    private int maximumNumber;
    private int amountOfNumbers;

    public WinningNumbersPropertyTestConfig(int minimumNumber, int maximumNumber, int amountOfNumbers) {
        this.minimumNumber = minimumNumber;
        this.maximumNumber = maximumNumber;
        this.amountOfNumbers = amountOfNumbers;
    }

    @Override
    public int getMinimumNumber() {
        return minimumNumber;
    }

    @Override
    public void setMinimumNumber(int minimumNumber) {
        this.minimumNumber = minimumNumber;
    }

    @Override
    public int getMaximumNumber() {
        return maximumNumber;
    }

    @Override
    public void setMaximumNumber(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    @Override
    public int getAmountOfNumbers() {
        return amountOfNumbers;
    }

    @Override
    public void setAmountOfNumbers(int amountOfNumbers) {
        this.amountOfNumbers = amountOfNumbers;
    }
}
