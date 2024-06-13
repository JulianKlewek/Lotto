package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

@AllArgsConstructor
class DateGenerator {

    private final int LOTTERY_HOUR = 20;
    private static final int HOUR_OFFSET = 1;
    private final int LOTTERY_MINUTES = 0;

    private final Clock clock;

    public LocalDateTime generateDrawDate(LocalDateTime ticketCreatedAt) {
        LocalDateTime now = LocalDateTime.now(clock);
        if (isFriday(now) && isEightPm(now)) {
            return now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                    .withHour(LOTTERY_HOUR)
                    .withMinute(LOTTERY_MINUTES);
        }
        return now.with(nextOrSame(DayOfWeek.FRIDAY))
                .withHour(LOTTERY_HOUR)
                .withMinute(LOTTERY_MINUTES);
    }

    private boolean isEightPm(LocalDateTime ticketCreatedTime) {
        return ticketCreatedTime.getHour() - HOUR_OFFSET == LOTTERY_HOUR;
    }

    private boolean isFriday(LocalDateTime ticketCreatedTime) {
        return ticketCreatedTime.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

}
