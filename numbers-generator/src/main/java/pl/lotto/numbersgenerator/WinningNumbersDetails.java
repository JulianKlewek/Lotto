package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("WinningNumber")
@Builder
@AllArgsConstructor
@ToString
class WinningNumbersDetails {
    List<Integer> numbers;
    @Indexed(unique = true)
    Instant drawDate;
    @Indexed(unique = true)
    Long lotteryNumber;
}
