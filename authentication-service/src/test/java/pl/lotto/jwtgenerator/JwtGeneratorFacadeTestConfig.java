package pl.lotto.jwtgenerator;

import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtGeneratorFacadeTestConfig {

    private final static String SECRET = "86345fbdcbb53a46adad6339d0157451588b17e1af15ce6ed373de9926bdf618";
    private final static long ACCESS_EXPIRATION = 2000;
    private final static long REFRESH_EXPIRATION = 10000;

    @Bean("jwtGeneratorClock")
    @Primary
    Clock clock() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JULY, 8, 11, 11, 11);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    JwtUtilsPropertyConfigurable utilsPropertyConfigurable = new JwtUtilsPropertyTestConfig(
            SECRET, ACCESS_EXPIRATION, REFRESH_EXPIRATION);
}
