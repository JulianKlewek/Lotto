package pl.lotto.numbersgenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
class NoSqlWinningNumbersRepository implements WinningNumbersRepository {

    private final MongoWinningNumbersRepository numbersRepository;

    public NoSqlWinningNumbersRepository(MongoWinningNumbersRepository numbersRepository) {
        this.numbersRepository = numbersRepository;
    }

    @Override
    public WinningNumbersDetails save(WinningNumbersDetails winningNumbersDetails) {
        return numbersRepository.save(winningNumbersDetails);
    }

    @Override
    public Optional<WinningNumbersDetails> findTopByOrderByLotteryNumberDesc() {
        return numbersRepository.findTopByOrderByLotteryNumberDesc();
    }

    @Override
    public WinningNumbersDetails findByDrawDate(Instant generatedTime) {
        return numbersRepository.findByDrawDate(generatedTime).orElseThrow(
                () -> new WinningNumbersNotFoundException("Winning numbers not found for given date"));
    }

    @Override
    public WinningNumbersDetails findByLotteryNumber(Long lotteryNumber) {
        return numbersRepository.findByLotteryNumber(lotteryNumber)
                .orElseThrow(() -> new WinningNumbersNotFoundException("Winning numbers not found for given lottery number"));
    }

    @Override
    public WinningNumbersDetails findFirstByOrderByDrawDate() {
        return numbersRepository.findFirstByOrderByDrawDate()
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not found any winning numbers"));
    }

    @Override
    public boolean existsByDrawDate(Instant drawDate) {
        return numbersRepository.existsByDrawDate(drawDate);
    }

    @Override
    public void deleteAll() {
        numbersRepository.deleteAll();
    }
}

@Repository
interface MongoWinningNumbersRepository extends MongoRepository<WinningNumbersDetails, Long> {

    Optional<WinningNumbersDetails> findTopByOrderByLotteryNumberDesc();

    Optional<WinningNumbersDetails> findByDrawDate(Instant generatedTime);

    Optional<WinningNumbersDetails> findByLotteryNumber(Long lotteryNumber);

    Optional<WinningNumbersDetails> findFirstByOrderByDrawDate();

    boolean existsByDrawDate(Instant drawDate);
}