package pl.lotto.numberreceiver.drawdategenerator;

import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

public interface DrawDateTestsConstants {


    static Stream<Arguments> VALID_NEXT_DRAW_DATES() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2024, Month.JUNE, 12, 20, 0),
                        LocalDateTime.of(2024, Month.JUNE, 14, 20, 0)),
                Arguments.of(LocalDateTime.of(2024, Month.JUNE, 14, 18, 59),
                        LocalDateTime.of(2024, Month.JUNE, 14, 20, 0)),
                Arguments.of(LocalDateTime.of(2024, Month.JUNE, 14, 19, 0),
                        LocalDateTime.of(2024, Month.JUNE, 21, 20, 0))
        );
    }

}
