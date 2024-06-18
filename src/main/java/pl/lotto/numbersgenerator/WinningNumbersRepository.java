package pl.lotto.numbersgenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbersDetails, Long> {

//    Optional<Long> findFirstOrderByLotteryNumberDesc();
    Optional<WinningNumbersDetails> findTopByOrderByLotteryNumberDesc();

    Optional<WinningNumbersDetails> findByDrawDate(Instant generatedTime);

    Optional<WinningNumbersDetails> findByLotteryNumber(Long lotteryNumber);

}
