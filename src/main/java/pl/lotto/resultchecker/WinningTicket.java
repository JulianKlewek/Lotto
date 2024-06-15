package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("WinningTicket")
@Builder
@AllArgsConstructor
@ToString
class WinningTicket {
    String hash;
    List<Integer> numbers;
    Instant drawDate;
    Long lotteryNumber;
    int amountOfCorrectNumbers;
    boolean collectedReward;
}
