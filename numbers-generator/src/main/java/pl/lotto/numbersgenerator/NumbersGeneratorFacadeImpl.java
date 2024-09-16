package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.numbersgenerator.dto.WinningNumbersResponse;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static pl.lotto.numbersgenerator.WinningNumbersMapper.toDto;

@Log4j2
@AllArgsConstructor
class NumbersGeneratorFacadeImpl implements NumbersGeneratorFacade {

    private final WinningNumbersGenerable winningNumbersGenerator;
    private final WinningNumbersRepository numbersRepository;
    private final DrawNumberGenerator drawNumberGenerator;
    private final Clock clock;

    @Override
    public WinningNumbersResponse generateWinningNumbers() {
        Instant createdAt = Instant.now(clock).truncatedTo(ChronoUnit.MINUTES);
        if (numbersRepository.existsByDrawDate(createdAt)) {
            log.info("Numbers are already generated for [{}].", createdAt);
            WinningNumbersDetails fetchedWinningNumbers = numbersRepository.findByDrawDate(createdAt);
            log.info("Fetched winning numbers for given date [{}].", fetchedWinningNumbers.numbers);
            return toDto(fetchedWinningNumbers);
        }
        Set<Integer> winningNumbersSet = winningNumbersGenerator.generateSixNumbersInGivenRange();
        Long drawNumber = drawNumberGenerator.generateDrawNumber();
        List<Integer> winningNumbers = List.copyOf(winningNumbersSet);
        log.info("Generated [{}] winning numbers for date: [{}]", winningNumbers, createdAt);
        WinningNumbersDetails winningNumberDetails = WinningNumbersDetails.builder()
                .numbers(winningNumbers)
                .lotteryNumber(drawNumber)
                .drawDate(createdAt)
                .build();
        WinningNumbersDetails saved = numbersRepository.save(winningNumberDetails);
        return toDto(saved);
    }

    @Override
    public WinningNumbersResponse getWinningNumbersForDate(Instant drawDate) {
        WinningNumbersDetails winningNumbers = numbersRepository.findByDrawDate(drawDate);
        return toDto(winningNumbers);
    }

    @Override
    public WinningNumbersResponse getWinningNumbersForLotteryNumber(Long lotteryId) {
        WinningNumbersDetails winningNumbers = numbersRepository.findByLotteryNumber(lotteryId);
        return toDto(winningNumbers);
    }

    @Override
    public Instant getLatestDrawDateWithGeneratedNumbers() {
        WinningNumbersDetails latestNumbers = numbersRepository.findFirstByOrderByDrawDate();
        return latestNumbers.drawDate;
    }
}
