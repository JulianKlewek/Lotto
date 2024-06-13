package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.*;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

@AllArgsConstructor
class DateGenerator {

    private static final int LOTTERY_HOUR = 20;
    private static final int HOUR_OFFSET = 1;
    private static final int LOTTERY_MINUTES = 0;

    private final Clock clock;

    public ZonedDateTime generateDrawDate(ZonedDateTime ticketCreatedAt) {
        if (isFriday(ticketCreatedAt) && isHourToDraw(ticketCreatedAt)) {
            return ticketCreatedAt.with(next(DayOfWeek.FRIDAY))
                    .withHour(LOTTERY_HOUR)
                    .withMinute(LOTTERY_MINUTES);
        }
        return ticketCreatedAt.with(nextOrSame(DayOfWeek.FRIDAY))
                .withHour(LOTTERY_HOUR)
                .withMinute(LOTTERY_MINUTES);
    }

    private boolean isHourToDraw(ZonedDateTime ticketCreatedTime) {
        return ticketCreatedTime.getHour() + HOUR_OFFSET == LOTTERY_HOUR;
    }

    private boolean isFriday(ZonedDateTime ticketCreatedTime) {
        return ticketCreatedTime.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

}
