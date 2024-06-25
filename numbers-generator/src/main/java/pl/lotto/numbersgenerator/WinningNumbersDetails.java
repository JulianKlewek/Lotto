package pl.lotto.numbersgenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.List;

@Document("WinningNumber")
@Builder
@AllArgsConstructor
@ToString
class WinningNumbersDetails {
    List<Integer> numbers;
    @Indexed(unique = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Instant drawDate;
    @Indexed(unique = true)
    Long lotteryNumber;
}
