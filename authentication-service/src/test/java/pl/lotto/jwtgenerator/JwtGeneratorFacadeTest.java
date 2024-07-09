package pl.lotto.jwtgenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.jwtgenerator.dto.JwtResponse;

import static org.assertj.core.api.Assertions.assertThat;

class JwtGeneratorFacadeTest extends JwtGeneratorFacadeTestConfig {

    @Test
    void should_return_access_token_for_given_user_and_date() {
        //given
        String expectedToken = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcyMDQzNzA3MSwiZXhwIjoxNzIwNDM3MDczfQ.MSnGcdK0dhcLOEoSJu5HSrcZWbptYLnPRJsLoSik6sXyK1AGaLLIAYKAoU6HHDvT";
        JwtGeneratorFacade jwtGeneratorFacade = new JwtGeneratorConfiguration()
                .createJwtGeneratorFacadeForTests(utilsPropertyConfigurable, clock());
        //when
        JwtResponse response = jwtGeneratorFacade.generateToken("user1", TokenType.ACCESS);
        //then
        assertThat(response.token()).isEqualTo(expectedToken);
    }
}