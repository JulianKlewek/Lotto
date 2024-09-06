package pl.lotto.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRegisterSuccessfullyIT extends BaseIntegrationTest {

    @Test
    @DisplayName("User creates account and confirms email")
    void happyPath_shouldUserInputRegistrationData_andReceive201Created() throws Exception {
        //step 1: user types registration data
        //given
        String username = "User1";
        String email = "user1@gmail.com";
        char[] password = "Password1!".toCharArray();
        UserRegisterRequest request = UserRegisterRequest.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        //when
        ResultActions perform = mockMvc.perform(post("/auth/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());
        //then
        perform.andExpectAll(
                        status().isCreated(),
                        jsonPath("$.message").value("ACCOUNT CREATED"),
                        jsonPath("$.username").value(username),
                        jsonPath("$.email").value(email))
                .andReturn();
        //step 1: user confirms email
        //given

    }
}
