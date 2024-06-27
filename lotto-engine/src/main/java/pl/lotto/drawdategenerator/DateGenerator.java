package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.*;

@AllArgsConstructor
class DateGenerator {

    private final DrawDatePropertyConfigurable propertyConfigurable;
    private static final int LOTTERY_NANO = 0;

    public Instant generateDrawDate(Instant ticketCreatedAt) {
        DayOfWeek drawDay = DayOfWeek.of(propertyConfigurable.getDrawDayOfWeek());
        if (isDrawDay(ticketCreatedAt, drawDay) && !isHourLessThanHourClosingReceivingTickets(ticketCreatedAt)) {
            return ticketCreatedAt.atZone(ZoneOffset.UTC)
                    .with(next(drawDay))
                    .withHour(propertyConfigurable.getDrawHour())
                    .withMinute(propertyConfigurable.getDrawMinute())
                    .withSecond(propertyConfigurable.getDrawSecond())
                    .withNano(LOTTERY_NANO)
                    .toInstant();
        }
        return ticketCreatedAt.atZone(ZoneOffset.UTC)
                .with(nextOrSame(drawDay))
                .withHour(propertyConfigurable.getDrawHour())
                .withMinute(propertyConfigurable.getDrawMinute())
                .withSecond(propertyConfigurable.getDrawSecond())
                .withNano(LOTTERY_NANO)
                .toInstant();
    }

    private boolean isHourLessThanHourClosingReceivingTickets(Instant ticketCreatedTime) {
        return ticketCreatedTime
                .atZone(ZoneOffset.UTC)
                .getHour() < propertyConfigurable.getDrawHour();
    }

    private boolean isDrawDay(Instant ticketCreatedTime, DayOfWeek drawDay) {
        return ticketCreatedTime
                .atZone(ZoneOffset.UTC)
                .getDayOfWeek()
                .equals(drawDay);
    }

}
