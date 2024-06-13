package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Set;

import static java.time.ZonedDateTime.*;
import static pl.lotto.numbersgenerator.WinningNumbersDetails.*;
import static pl.lotto.numbersgenerator.WinningNumbersMapper.*;

@AllArgsConstructor
class NumbersGeneratorFacadeImpl implements NumbersGeneratorFacade {

    private final WinningNumbersGenerator winningNumbersGenerator;
    private final WinningNumbersRepository numbersRepository;
    private final Clock clock;

    @Override
    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = winningNumbersGenerator.generateSixNumbersInGivenRange();
        Long drawNumber = winningNumbersGenerator.generateDrawNumber(numbersRepository);
        ZonedDateTime createdAt = now(clock);
        WinningNumbersDetails winningNumberDetails = builder()
                .numbers(winningNumbers)
                .lotteryNumber(drawNumber)
                .generatedTime(createdAt)
                .build();
        numbersRepository.save(winningNumberDetails);
        return toDto(winningNumberDetails);
    }
}