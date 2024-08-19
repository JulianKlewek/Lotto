package pl.lotto.numbersgenerator;

import java.time.Instant;
import java.util.Optional;

public interface WinningNumbersRepository {

    WinningNumbersDetails save(WinningNumbersDetails winningNumbersDetails);

    Optional<WinningNumbersDetails> findTopByOrderByLotteryNumberDesc();

    WinningNumbersDetails findByDrawDate(Instant generatedTime);

    WinningNumbersDetails findByLotteryNumber(Long lotteryNumber);

    WinningNumbersDetails findFirstByOrderByDrawDate();

    boolean existsByDrawDate(Instant drawDate);

    void deleteAll();
}
