package pl.lotto.feature;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsersPlayLottoIntegrationTest extends BaseIntegrationTest {

    @Test
    void should_user_play_and_win_in_next_draw_date() throws Exception {
        //step 1: user types six number
        //given
        List<Integer> typedNumbers = List.of(1,2,3,4,5,6);
        String expectedDrawDate = "2024-06-14T20:00:00Z";
        //when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("{\"inputNumbers\":" + typedNumbers + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        perform.andExpectAll(
                status().isOk(),
                jsonPath("$.status").value("success"),
                jsonPath("$.errorsList", hasSize(0)),
                jsonPath("$.ticket.hash", matchesPattern(UUID_REGEX)),
                jsonPath("$.ticket.numbers", contains(typedNumbers.toArray())),
                jsonPath("$.ticket.drawDate").value(expectedDrawDate),
                jsonPath("$.ticket.message").value(nullValue())
        );

        //step 2: system generates winning numbers
        //given
        //when
        //then

        //step 1: user check results and receives data that he won
        //given
        //when
        //then
    }
}
