package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public interface DrawDateTestsConstants {

    LocalDateTime firstFridayDraw = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0);
    ZonedDateTime ticketCreatedAt1 = ZonedDateTime.of(2024, 6, 13, 20, 0, 0, 0, ZoneId.of("Europe/Warsaw"));
    ZonedDateTime ticketCreatedAt2 = ZonedDateTime.of(2024, 6, 14, 18, 50, 0, 0, ZoneId.of("Europe/Warsaw"));
    ZonedDateTime ticketCreatedLessThanHourBeforeDraw = ZonedDateTime.of(2024, 6, 14, 19, 1, 0, 0, ZoneId.of("Europe/Warsaw"));
    LocalDateTime nextWeekFridayDraw = LocalDateTime.of(2024, Month.JUNE, 21, 20, 0);

    static Stream<Arguments> VALID_NEXT_DRAW_DATES() {
        return Stream.of(
                Arguments.of(ticketCreatedAt1,
                        ZonedDateTime.of(firstFridayDraw, ZoneId.of("Europe/Warsaw"))),
                Arguments.of(ticketCreatedAt2,
                        ZonedDateTime.of(firstFridayDraw, ZoneId.of("Europe/Warsaw"))),
                Arguments.of(ticketCreatedLessThanHourBeforeDraw,
                        ZonedDateTime.of(nextWeekFridayDraw, ZoneId.of("Europe/Warsaw")))
        );
    }

}
