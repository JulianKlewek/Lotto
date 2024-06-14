package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.*;

@AllArgsConstructor
class DateGenerator {

    private static final int LOTTERY_HOUR = 20;
    private static final int LOTTERY_MINUTES = 0;
    private static final int LOTTERY_SECONDS = 0;
    private static final int LOTTERY_NANO = 0;

    private final Clock clock;

    public Instant generateDrawDate(Instant ticketCreatedAt) {
        if (isFriday(ticketCreatedAt) && isLessThanHourBeforeDraw(ticketCreatedAt)) {
            return ticketCreatedAt.atZone(ZoneOffset.UTC)
                    .with(next(DayOfWeek.FRIDAY))
                    .withHour(LOTTERY_HOUR)
                    .withMinute(LOTTERY_MINUTES)
                    .withSecond(LOTTERY_SECONDS)
                    .withNano(LOTTERY_NANO)
                    .toInstant();
        }
        return ticketCreatedAt.atZone(ZoneOffset.UTC)
                .with(nextOrSame(DayOfWeek.FRIDAY))
                .withHour(LOTTERY_HOUR)
                .withMinute(LOTTERY_MINUTES)
                .withSecond(LOTTERY_SECONDS)
                .withNano(LOTTERY_NANO)
                .toInstant();

    }

    private boolean isLessThanHourBeforeDraw(Instant ticketCreatedTime) {
        return LOTTERY_HOUR - ticketCreatedTime
                .atZone(ZoneOffset.UTC)
                .getHour() == 1;
    }

    private boolean isFriday(Instant ticketCreatedTime) {
        return ticketCreatedTime
                .atZone(ZoneOffset.UTC)
                .getDayOfWeek()
                .equals(DayOfWeek.FRIDAY);
    }

}
