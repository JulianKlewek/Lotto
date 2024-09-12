package pl.lotto.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.infrastructure.emailsenderservice.dto.AccountCreatedEvent;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRegisterSuccessfullyIT extends BaseIntegrationTest {

    @Test
    @DisplayName("User creates account and confirms email")
    void happyPath_shouldUserCreateAccountConfirmEmail_andReceive200Ok() throws Exception {
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
        ResultActions performRegister = mockMvc.perform(post("/auth/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());
        //then
        performRegister.andExpectAll(
                        status().isCreated(),
                        jsonPath("$.message").value("ACCOUNT CREATED"),
                        jsonPath("$.username").value(username),
                        jsonPath("$.email").value(email))
                .andReturn();

        //step 2: user confirms email
        //given
        AccountCreatedEvent sentMessage = (AccountCreatedEvent) rabbitTemplate.receiveAndConvert(rabbitQueueName);
        assert sentMessage != null;
        String generatedToken = sentMessage.token();
        //when
        ResultActions performConfirmation = mockMvc.perform(get("/auth/confirm-account")
                        .param("token", generatedToken))
                .andDo(print());
        //then
        performConfirmation.andExpectAll(
                        status().isOk(),
                        jsonPath("$.username").value(username),
                        jsonPath("$.success").value(true),
                        jsonPath("$.errors").isEmpty())
                .andReturn();
    }
}
