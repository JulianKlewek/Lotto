package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static pl.lotto.numbersgenerator.WinningNumbersMapper.toDto;

@AllArgsConstructor
class NumbersGeneratorFacadeImpl implements NumbersGeneratorFacade {

    private final WinningNumbersGenerable winningNumbersGenerator;
    private final WinningNumbersRepository numbersRepository;
    private final DrawNumberGenerator drawNumberGenerator;
    private final Clock clock;

    @Override
    public WinningNumbersResponseDto generateWinningNumbers() {
        Instant createdAt = Instant.now(clock).truncatedTo(ChronoUnit.MINUTES);
        if (numbersRepository.existsByDrawDate(createdAt)) {
            WinningNumbersDetails fetchedWinningNumbers = numbersRepository.findByDrawDate(createdAt).orElseThrow(
                    () -> new WinningNumbersNotFoundException("Could not fetch winning numbers for given date"));
            return toDto(fetchedWinningNumbers);
        }
        Set<Integer> winningNumbersSet = winningNumbersGenerator.generateSixNumbersInGivenRange();
        Long drawNumber = drawNumberGenerator.generateDrawNumber();
        List<Integer> winningNumbers = List.copyOf(winningNumbersSet);
        WinningNumbersDetails winningNumberDetails = WinningNumbersDetails.builder()
                .numbers(winningNumbers)
                .lotteryNumber(drawNumber)
                .drawDate(createdAt)
                .build();
        WinningNumbersDetails saved = numbersRepository.save(winningNumberDetails);
        return toDto(saved);
    }

    @Override
    public WinningNumbersResponseDto getWinningNumbersForDate(Instant drawDate) {
        WinningNumbersDetails winningNumbers = numbersRepository.findByDrawDate(drawDate).orElseThrow(
                () -> new WinningNumbersNotFoundException("Winning numbers not found"));
        return toDto(winningNumbers);
    }

    @Override
    public WinningNumbersResponseDto getWinningNumbersForLotteryNumber(Long lotteryId) {
        WinningNumbersDetails winningNumbers = numbersRepository.findByLotteryNumber(lotteryId).orElseThrow(
                () -> new WinningNumbersNotFoundException("Winning numbers not found"));
        return toDto(winningNumbers);
    }

    @Override
    public Instant getLatestDrawDateWithGeneratedNumbers() {
        WinningNumbersDetails latestNumbers = numbersRepository.findFirstByOrderByDrawDate().orElseThrow(
                () -> new WinningNumbersNotFoundException("Not found any winning numbers"));
        Instant latestDrawDate = latestNumbers.drawDate;
        return latestDrawDate;
    }
}
