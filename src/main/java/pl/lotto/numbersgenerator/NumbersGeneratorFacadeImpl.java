package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
        Long drawNumber = winningNumbersGenerator.generateDrawNumber();
        Instant createdAt = Instant.now(clock)
                .truncatedTo(ChronoUnit.MINUTES);
        WinningNumbersDetails winningNumberDetails = WinningNumbersDetails.builder()
                .numbers(winningNumbers)
                .lotteryNumber(drawNumber)
                .drawDate(createdAt)
                .build();
        WinningNumbersDetails saved = numbersRepository.save(winningNumberDetails);
        return toDto(saved);
    }

    @Override
    public WinningNumbersDto getWinningNumbersForDate(ZonedDateTime drawDate) {
        Instant dateInstant = drawDate.toInstant();
        WinningNumbersDetails winningNumbers = numbersRepository.findByGeneratedTime(dateInstant).orElseThrow(
                () -> new WinningNumbersNotFoundException("Winning numbers not found"));
        return toDto(winningNumbers);
    }
}
