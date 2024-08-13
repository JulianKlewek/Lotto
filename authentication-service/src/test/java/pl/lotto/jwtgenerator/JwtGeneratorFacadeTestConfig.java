package pl.lotto.jwtgenerator;

import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtGeneratorFacadeTestConfig {

    private final static String SECRET = "secret1111cbd892523fe1ed272a99b10e0d35c7a0efd3bd34748b79c0fcace1d80547cbc97779697bc203cad6f84a1133da91d2b076c18ca9de86ee3d84f6413b5515ffd74232f6e083fc2f57009e87db0e8437a4cbddbdc94758089af3c5a9a460736cf44fa70d3c08822e71391fcfa790d2cb31364a21734d50155bd1c660ab6991d85a9b527baf72797657cf2be7b3c55137ec1b70dd7cbbef93fc75115588b9b10e86e359d06497a1eb54d1300dd70b5d06cc55ba2c833474a8905c93031efc108b2889301c3d390036fdd3981c946aae38b20621567c99d2180879852235c3b0b72a4edf6fa30dd80f9c48279229a539b502e355df1f37cb9648fc7029";
    private final static long ACCESS_EXPIRATION = 120000;
    private final static long REFRESH_EXPIRATION = 600000;

    @Bean("jwtGeneratorClock")
    @Primary
    Clock clock() {
        LocalDateTime today = LocalDateTime.of(2024, Month.JULY, 8, 11, 11, 11);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    protected final JwtUtilsPropertyConfigurable utilsPropertyConfigurable = new JwtUtilsPropertyTestConfig(
            SECRET, ACCESS_EXPIRATION, REFRESH_EXPIRATION);
}
