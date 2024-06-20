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

    public Instant generateDrawDate(Instant ticketCreatedAt) {
        if (isFriday(ticketCreatedAt) && !isHourLessThanHourClosingReceivingTickets(ticketCreatedAt)) {
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

    private boolean isHourLessThanHourClosingReceivingTickets(Instant ticketCreatedTime) {
        return ticketCreatedTime
                .atZone(ZoneOffset.UTC)
                .getHour() < LOTTERY_HOUR;
    }

    private boolean isFriday(Instant ticketCreatedTime) {
        return ticketCreatedTime
                .atZone(ZoneOffset.UTC)
                .getDayOfWeek()
                .equals(DayOfWeek.FRIDAY);
    }

}
