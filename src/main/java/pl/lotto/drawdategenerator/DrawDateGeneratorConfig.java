package pl.lotto.drawdategenerator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DrawDateGeneratorConfig {

    @Bean(name = "drawDateClock")
    Clock clock() {
        return Clock.systemUTC();
    }

    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacadeForTest(@Qualifier("drawDateClock") Clock clock) {
        DateGenerator dateGenerator = new DateGenerator(clock);
        return new DrawDateGeneratorFacadeImpl(dateGenerator);
    }

    @Bean(name = "DrawDateGenForProd")
    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacade(@Qualifier("drawDateClock")Clock clock) {
        DateGenerator dateGenerator = new DateGenerator(clock);
        return new DrawDateGeneratorFacadeImpl(dateGenerator);
    }
}
