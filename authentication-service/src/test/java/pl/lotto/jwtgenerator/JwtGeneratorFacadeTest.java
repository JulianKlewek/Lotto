package pl.lotto.jwtgenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;

import static org.assertj.core.api.Assertions.assertThat;

class JwtGeneratorFacadeTest extends JwtGeneratorFacadeTestConfig {

    @Test
    void should_return_access_token_for_given_user_and_date() {
        //given
        String expectedToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcyMDQzNzA3MSwiZXhwIjoxNzIwNDM3MTkxfQ.LvdHq596zwBpHhhas-WvgQMKZRswhkQxJe_ZKfHgSJsGSCmxkFmOxQk6MgBGT5gLeLCx8zFLBEwYkAlaYb3DGg";
        JwtGeneratorFacade jwtGeneratorFacade = new JwtGeneratorConfiguration()
                .createJwtGeneratorFacadeForTests(utilsPropertyConfigurable, clock());
        UserTokenRequest user1 = new UserTokenRequest("user1", TokenType.ACCESS.toString());
        //when
        JwtResponse response = jwtGeneratorFacade.generateToken(user1);
        //then
        assertThat(response.token()).isEqualTo(expectedToken);
    }
}