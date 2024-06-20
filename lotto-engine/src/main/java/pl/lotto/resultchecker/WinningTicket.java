package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("WinningTicket")
@Builder
@AllArgsConstructor
@ToString
class WinningTicket {
    @Id
    String hash;
    List<Integer> userNumbers;
    Instant drawDate;
    Long lotteryNumber;
    int amountOfCorrectNumbers;
    boolean collectedReward;
}
