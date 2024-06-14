package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.*;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

@AllArgsConstructor
class DateGenerator {

    private static final int LOTTERY_HOUR = 20;
    private static final int LOTTERY_MINUTES = 0;
    private static final int LOTTERY_SECONDS = 0;
    private static final int LOTTERY_NANO = 0;

    private final Clock clock;

    public ZonedDateTime generateDrawDate(ZonedDateTime ticketCreatedAt) {
        if (isFriday(ticketCreatedAt) && isLessThanHourBeforeDraw(ticketCreatedAt)) {
            return ticketCreatedAt.with(next(DayOfWeek.FRIDAY))
                    .withHour(LOTTERY_HOUR)
                    .withMinute(LOTTERY_MINUTES)
                    .withSecond(LOTTERY_SECONDS)
                    .withNano(LOTTERY_NANO);
        }
        return ticketCreatedAt.with(nextOrSame(DayOfWeek.FRIDAY))
                .withHour(LOTTERY_HOUR)
                .withMinute(LOTTERY_MINUTES)
                .withSecond(LOTTERY_SECONDS)
                .withNano(LOTTERY_NANO);
    }

    private boolean isLessThanHourBeforeDraw(ZonedDateTime ticketCreatedTime) {
        return LOTTERY_HOUR - ticketCreatedTime.getHour() == 1;
    }

    private boolean isFriday(ZonedDateTime ticketCreatedTime) {
        return ticketCreatedTime.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

}
