package pl.lotto.jwtgenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;

import static org.assertj.core.api.Assertions.assertThat;

class JwtGeneratorFacadeTest extends JwtGeneratorFacadeTestConfig {

    @Test
    void should_return_access_token_for_given_user_and_date() {
        //given
        String expectedToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNzIwNDM3MDcxLCJleHAiOjE3MjA0MzcxOTF9.XhKxGNDsNF60yHgrmhL6QFlHXznv7shZRZsdImnm6VKWeAq1wP4U-iRbYnNI-q-smAWJCw9VU2eBA7h2ZQID2g";
        JwtGeneratorFacade jwtGeneratorFacade = new JwtGeneratorConfiguration()
                .createJwtGeneratorFacadeForTests(utilsPropertyConfigurable, clock());
        UserTokenRequest user1 = new UserTokenRequest(1L, TokenType.ACCESS.toString());
        //when
        JwtResponse response = jwtGeneratorFacade.generateToken(user1);
        //then
        assertThat(response.token()).isEqualTo(expectedToken);
    }
}