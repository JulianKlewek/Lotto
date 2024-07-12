package pl.lotto.userauth;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.lotto.jwtgenerator.JwtGeneratorFacade;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;
import pl.lotto.userauthenticator.UserAuthConfiguration;
import pl.lotto.userauthenticator.UserAuthFacade;
import pl.lotto.userauthenticator.UserDetailsImpl;
import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAuthFacadeTest extends UserAuthTestConfig {

    @Test
    void should_register_user_and_return_response_dto() {
        //given
        JwtGeneratorFacade jwtGeneratorFacade = mock(JwtGeneratorFacade.class);
        UserAuthFacade userAuthFacade = new UserAuthConfiguration()
                .createUserAuthFacadeForTests(userRepository, jwtGeneratorFacade);
        UserRegisterRequest user1 = UserRegisterRequest.builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("Password".toCharArray())
                .build();
        //when
        UserRegisterResponse response = userAuthFacade.register(user1);
        //then
        assertThat(response.username()).isEqualTo(user1.username());
        assertThat(response.email()).isEqualTo(user1.email());

    }

    @Test
    void should_return_user_info_and_token() {
        //given
        JwtGeneratorFacade jwtGeneratorFacade = mock(JwtGeneratorFacade.class);
        UserAuthFacade userAuthFacade = new UserAuthConfiguration()
                .createUserAuthFacadeForTests(userRepository, jwtGeneratorFacade);
        UserDetailsImpl user1 = UserDetailsImpl.builder()
                .username("user1")
                .email("user1@gmail.com")
                .build();
        UserTokenRequest accessTokenRequest = UserTokenRequest.builder()
                .username("user1")
                .tokenType("ACCESS")
                .build();
        String accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcyMDQzNzA3MSwiZXhwIjoxNzIwNDM3MTkxfQ.LvdHq596zwBpHhhas-WvgQMKZRswhkQxJe_ZKfHgSJsGSCmxkFmOxQk6MgBGT5gLeLCx8zFLBEwYkAlaYb3DGg";
        JwtResponse accessTokenResponse = JwtResponse.builder()
                .token(accessToken)
                .build();
        //when
        when(jwtGeneratorFacade.generateToken(accessTokenRequest)).thenReturn(accessTokenResponse);
        UserLoginResponse loginResponse = userAuthFacade.generateLoginResponse(user1);
        //then
        Assertions.assertThat(loginResponse.accessToken()).isEqualTo(accessToken);
        Assertions.assertThat(loginResponse.user().username()).isEqualTo(user1.getUsername());
        Assertions.assertThat(loginResponse.user().email()).isEqualTo(user1.getEmail());
    }
}
