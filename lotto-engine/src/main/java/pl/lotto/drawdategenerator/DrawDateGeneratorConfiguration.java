package pl.lotto.drawdategenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DrawDateGeneratorConfiguration {

    @Bean
    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacade() {
        DateGenerator dateGenerator = new DateGenerator();
        return new DrawDateGeneratorFacadeImpl(dateGenerator);
    }

    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacadeForTest() {
        return drawDateGeneratorFacade();
    }
}
