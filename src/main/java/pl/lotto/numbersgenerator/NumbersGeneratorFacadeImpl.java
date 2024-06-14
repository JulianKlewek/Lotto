package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.Clock;
import java.time.Instant;
import java.util.Set;

import static pl.lotto.numbersgenerator.WinningNumbersMapper.toDto;

@AllArgsConstructor
class NumbersGeneratorFacadeImpl implements NumbersGeneratorFacade {

    private final WinningNumbersGenerator winningNumbersGenerator;
    private final WinningNumbersRepository numbersRepository;
    private final Clock clock;

    @Override
    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = winningNumbersGenerator.generateSixNumbersInGivenRange();
        Long drawNumber = winningNumbersGenerator.generateDrawNumber(numbersRepository);
        Instant createdAt = Instant.now(clock);
        WinningNumbersDetails winningNumberDetails = WinningNumbersDetails.builder()
                .numbers(winningNumbers)
                .lotteryNumber(drawNumber)
                .generatedTime(createdAt)
                .build();
        WinningNumbersDetails saved = numbersRepository.save(winningNumberDetails);
        return toDto(saved);
    }
}
