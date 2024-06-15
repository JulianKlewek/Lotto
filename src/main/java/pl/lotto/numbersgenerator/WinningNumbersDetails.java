package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document("WinningNumbers")
@Builder
@AllArgsConstructor
@ToString
class WinningNumbersDetails {
    Set<Integer> numbers;
    Instant drawDate;
    Long lotteryNumber;
}
