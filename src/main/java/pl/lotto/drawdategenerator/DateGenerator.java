package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.*;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

@AllArgsConstructor
class DateGenerator {

    private final int LOTTERY_HOUR = 20;
    private static final int HOUR_OFFSET = 1;
    private final int LOTTERY_MINUTES = 0;

    private final Clock clock;

    public ZonedDateTime generateDrawDate(ZonedDateTime ticketCreatedAt) {
//        Instant time = Instant.now(clock);
//        LocalDateTime currentLocalTime = LocalDateTime.now(clock).atOffset(ZoneOffset.UTC).toLocalDateTime();
        ZonedDateTime now = ZonedDateTime.now(clock);

        if (isFriday(now) && isHourToDraw(now)) {
            return now.with(next(DayOfWeek.FRIDAY))
                    .withHour(LOTTERY_HOUR)
                    .withMinute(LOTTERY_MINUTES);
        }
        return now.with(nextOrSame(DayOfWeek.FRIDAY))
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
