package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("WinningNumbers")
@Builder
@AllArgsConstructor
@ToString
class WinningNumbersDetails {
    List<Integer> numbers;
    Instant drawDate;
    Long lotteryNumber;
}
