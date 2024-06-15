package pl.lotto.drawdategenerator;

import org.junit.jupiter.params.provider.Arguments;

import java.time.*;
import java.util.stream.Stream;

public interface DrawDateTestsConstants {

    LocalDateTime thisWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 14, 20, 0);
    ZonedDateTime ticketCreatedMoreThanHourBeforeDrawDate1 = ZonedDateTime.of(2024, 6, 13, 20, 0, 0, 0, ZoneOffset.UTC);
    ZonedDateTime ticketCreatedMoreThanHourBeforeDrawDate2 = ZonedDateTime.of(2024, 6, 14, 18, 59, 0, 0, ZoneOffset.UTC);
    LocalDateTime nextWeekDrawDate = LocalDateTime.of(2024, Month.JUNE, 21, 20, 0);
    ZonedDateTime ticketCreatedLessThanHourBeforeDraw = ZonedDateTime.of(2024, 6, 14, 19, 1, 0, 0, ZoneOffset.UTC);
    ZonedDateTime ticketCreatedHourBeforeDraw = ZonedDateTime.of(2024, 6, 14, 19, 0, 0, 0, ZoneOffset.UTC);

    static Stream<Arguments> VALID_THIS_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(ticketCreatedMoreThanHourBeforeDrawDate1,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(ticketCreatedMoreThanHourBeforeDrawDate2,
                        ZonedDateTime.of(thisWeekDrawDate, ZoneOffset.UTC))
        );
    }

    static Stream<Arguments> VALID_NEXT_WEEK_DRAW_DATES() {
        return Stream.of(
                Arguments.of(ticketCreatedLessThanHourBeforeDraw,
                        ZonedDateTime.of(nextWeekDrawDate, ZoneOffset.UTC)),
                Arguments.of(ticketCreatedHourBeforeDraw,
                        ZonedDateTime.of(nextWeekDrawDate, ZoneOffset.UTC))
        );
    }

}
