package pl.lotto.infrastructure.propertyconfigs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numbersgenerator.WinningNumbersPropertyConfigurable;

@Configuration
@ConfigurationProperties(prefix = "lotto.numbers")
public class WinningNumbersPropertyConfig implements WinningNumbersPropertyConfigurable {

    private int minimumNumber;
    private int maximumNumber;
    private int amountOfNumbers;

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
