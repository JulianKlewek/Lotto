package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Set;

@Document("WinningNumbers")
@Builder
@AllArgsConstructor
class WinningNumbersDetails {
    Set<Integer> numbers;
    ZonedDateTime generatedTime;
    Long lotteryNumber;
}
