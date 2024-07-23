package pl.lotto.userauthenticator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.lotto.jwtgenerator.JwtGeneratorFacade;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;
import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
    void should_not_register_user_and_return_user_already_exists_exception_for_email() {
        //given
        JwtGeneratorFacade jwtGeneratorFacade = mock(JwtGeneratorFacade.class);
        UserAuthFacade userAuthFacade = new UserAuthConfiguration()
                .createUserAuthFacadeForTests(userRepository, jwtGeneratorFacade);
        UserRegisterRequest defaultuser1 = UserRegisterRequest.builder()
                .username("defaultusername1")
                .email("defaultemail@gmail.com")
                .password("Password".toCharArray())
                .build();
        String exceptionMessage = "Email already exists: " + defaultuser1.email();
        //when&then
        assertThatExceptionOfType(UserAlreadyExistsException.class)
                .isThrownBy(() -> userAuthFacade.register(defaultuser1))
                .withMessage(exceptionMessage);
    }

    @Test
    void should_not_register_user_and_return_user_already_exists_exception_for_username() {
        //given
        JwtGeneratorFacade jwtGeneratorFacade = mock(JwtGeneratorFacade.class);
        UserAuthFacade userAuthFacade = new UserAuthConfiguration()
                .createUserAuthFacadeForTests(userRepository, jwtGeneratorFacade);
        UserRegisterRequest defaultuser = UserRegisterRequest.builder()
                .username("defaultusername")
                .email("defaultemail1@gmail.com")
                .password("Password".toCharArray())
                .build();
        String exceptionMessage = "Username already exists: " + defaultuser.username();
        //when&then
        assertThatExceptionOfType(UserAlreadyExistsException.class)
                .isThrownBy(() -> userAuthFacade.register(defaultuser))
                .withMessage(exceptionMessage);
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
        UserLoginResponse loginResponse = userAuthFacade.prepareLoginResponse(user1);
        //then
        Assertions.assertThat(loginResponse.accessToken()).isEqualTo(accessToken);
        Assertions.assertThat(loginResponse.user().username()).isEqualTo(user1.getUsername());
        Assertions.assertThat(loginResponse.user().email()).isEqualTo(user1.getEmail());
    }
}
