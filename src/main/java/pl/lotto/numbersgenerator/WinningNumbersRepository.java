package pl.lotto.numbersgenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbersDetails, Long> {

    Long findFirstByLotteryNumberOrderByLotteryNumberDesc();

}
