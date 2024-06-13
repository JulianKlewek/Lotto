package pl.lotto.drawdategenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DrawDateGeneratorConfig {

    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacadeForTest(Clock clock) {
        DateGenerator dateGenerator = new DateGenerator(clock);
        return new DrawDateGeneratorFacadeImpl(dateGenerator);
    }

    @Bean(name = "DrawDateGenForProd")
    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacade(Clock clock) {
        DateGenerator dateGenerator = new DateGenerator(clock);
        return new DrawDateGeneratorFacadeImpl(dateGenerator);
    }
}
