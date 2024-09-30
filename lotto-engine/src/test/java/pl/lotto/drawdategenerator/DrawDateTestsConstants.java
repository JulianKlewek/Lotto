package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.provider.Arguments;

import java.time.*;
import java.util.stream.Stream;

interface DrawDateTestsConstants {

    LocalDateTime previousWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 7, 20, 0);
    LocalDateTime thisWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0);
    LocalDateTime nextWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 21, 20, 0);
    ZonedDateTime timeMoreThanHourBeforeDrawDate1 = ZonedDateTime.of(2024, 6, 13, 20, 0, 0, 0, ZoneOffset.UTC);
    ZonedDateTime timeMoreThanHourBeforeDrawDate2 = ZonedDateTime.of(2024, 6, 14, 18, 59, 0, 0, ZoneOffset.UTC);
    ZonedDateTime timeEqualsDrawTime = ZonedDateTime.of(2024, 6, 14, 20, 0, 0, 0, ZoneOffset.UTC);
    ZonedDateTime timeHourAfterDraw = ZonedDateTime.of(2024, 6, 14, 21, 0, 0, 0, ZoneOffset.UTC);

    static Stream<Arguments> VALID_CURRENT_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(timeMoreThanHourBeforeDrawDate1,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(timeMoreThanHourBeforeDrawDate2,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC))
        );
    }

    static Stream<Arguments> VALID_NEXT_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(timeEqualsDrawTime,
                        ZonedDateTime.of(nextWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(timeHourAfterDraw,
                        ZonedDateTime.of(nextWeekDrawDate, ZoneOffset.UTC))
        );
    }

    static Stream<Arguments> VALID_LATEST_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(timeMoreThanHourBeforeDrawDate1,
                        ZonedDateTime.of(previousWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(timeEqualsDrawTime,
                        ZonedDateTime.of(previousWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(timeHourAfterDraw,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC))
        );
    }

}
