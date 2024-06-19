package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.provider.Arguments;

import java.time.*;
import java.util.stream.Stream;

interface DrawDateTestsConstants {

    LocalDateTime thisWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0);
    ZonedDateTime ticketCreatedMoreThanHourBeforeDrawDate1 = ZonedDateTime.of(2024, 6, 13, 20, 0, 0, 0, ZoneOffset.UTC);
    ZonedDateTime ticketCreatedMoreThanHourBeforeDrawDate2 = ZonedDateTime.of(2024, 6, 14, 18, 59, 0, 0, ZoneOffset.UTC);
    LocalDateTime nextWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 21, 20, 0);
    ZonedDateTime ticketCreatedAtTimeOfDraw = ZonedDateTime.of(2024, 6, 14, 20, 0, 0, 0, ZoneOffset.UTC);
    ZonedDateTime ticketCreatedHourAfterDraw = ZonedDateTime.of(2024, 6, 14, 21, 0, 0, 0, ZoneOffset.UTC);

    static Stream<Arguments> VALID_CURRENT_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(ticketCreatedMoreThanHourBeforeDrawDate1,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(ticketCreatedMoreThanHourBeforeDrawDate2,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC))
        );
    }

    static Stream<Arguments> VALID_NEXT_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(ticketCreatedAtTimeOfDraw,
                        ZonedDateTime.of(nextWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(ticketCreatedHourAfterDraw,
                        ZonedDateTime.of(nextWeekDrawDate, ZoneOffset.UTC))
        );
    }

}
