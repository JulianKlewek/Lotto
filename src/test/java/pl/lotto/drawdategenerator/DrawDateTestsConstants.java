package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.provider.Arguments;

import java.time.*;
import java.util.stream.Stream;

public interface DrawDateTestsConstants {

    LocalDateTime firstFridayDraw = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0);
    ZonedDateTime ticketCreatedAt1 = ZonedDateTime.of(2024, 6, 13, 20, 0, 0, 0, ZoneOffset.UTC);
    ZonedDateTime ticketCreatedAt2 = ZonedDateTime.of(2024, 6, 14, 18, 50, 0, 0, ZoneOffset.UTC);
    ZonedDateTime ticketCreatedLessThanHourBeforeDraw = ZonedDateTime.of(2024, 6, 14, 19, 1, 0, 0, ZoneOffset.UTC);
    LocalDateTime nextWeekFridayDraw = LocalDateTime.of(2024, Month.JUNE, 21, 20, 0);

    static Stream<Arguments> VALID_NEXT_DRAW_DATES() {
        return Stream.of(
                Arguments.of(ticketCreatedAt1,
                        ZonedDateTime.of(firstFridayDraw, ZoneOffset.UTC)),
                Arguments.of(ticketCreatedAt2,
                        ZonedDateTime.of(firstFridayDraw, ZoneOffset.UTC)),
                Arguments.of(ticketCreatedLessThanHourBeforeDraw,
                        ZonedDateTime.of(nextWeekFridayDraw, ZoneOffset.UTC))
        );
    }

}
