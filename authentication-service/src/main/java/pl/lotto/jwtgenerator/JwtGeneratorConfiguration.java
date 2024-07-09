package pl.lotto.jwtgenerator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class JwtGeneratorConfiguration {

    @Bean("jwtGeneratorClock")
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public JwtGeneratorFacade createJwtGeneratorFacade(JwtUtilsPropertyConfigurable propertyConfigurable,
                                                       @Qualifier("jwtGeneratorClock") Clock clock) {
        JwtUtils jwtUtils = new JwtUtils(clock, propertyConfigurable);
        return new JwtGeneratorFacadeImpl(jwtUtils);
    }

    public JwtGeneratorFacade createJwtGeneratorFacadeForTests(JwtUtilsPropertyConfigurable propertyConfigurable,
                                                               @Qualifier("jwtGeneratorClock") Clock clock) {
        return createJwtGeneratorFacade(propertyConfigurable, clock);
    }
}
