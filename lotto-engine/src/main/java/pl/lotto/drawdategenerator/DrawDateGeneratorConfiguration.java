package pl.lotto.drawdategenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DrawDateGeneratorConfiguration {

    @Bean
    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacade(DrawDatePropertyConfigurable propertyConfigurable) {
        DateGenerator dateGenerator = new DateGenerator(propertyConfigurable);
        return new DrawDateGeneratorFacadeImpl(dateGenerator);
    }

    public DrawDateGeneratorFacadeImpl drawDateGeneratorFacadeForTest(DrawDatePropertyConfigurable propertyConfigurable) {
        return drawDateGeneratorFacade(propertyConfigurable);
    }
}
