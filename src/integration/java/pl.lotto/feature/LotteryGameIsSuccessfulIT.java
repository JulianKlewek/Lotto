package pl.lotto.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numbersgenerator.WinningNumbersNotFoundException;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LotteryGameIsSuccessfulIT extends BaseIntegrationTest {

    @Test
    @DisplayName("should user win when he inputs 6 winning numbers and retrieves results after the draw date")
    void happyPath_shouldUserInputNumbers_andWin() throws Exception {
        //step 1: user types six number
        //given
        List<Integer> typedNumbers = List.of(1, 2, 3, 4, 5, 6);
        String expectedDrawDate = "2024-06-14T20:00:00Z";
        //when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("{\"inputNumbers\":" + typedNumbers + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        MvcResult mvcResult = perform.andExpectAll(
                        status().isOk(),
                        jsonPath("$.status").value("success"),
                        jsonPath("$.errorsList", hasSize(0)),
                        jsonPath("$.ticket.hash", matchesPattern(UUID_REGEX)),
                        jsonPath("$.ticket.numbers", contains(typedNumbers.toArray())),
                        jsonPath("$.ticket.drawDate", equalTo(expectedDrawDate)),
                        jsonPath("$.ticket.message", equalTo((null))))
                .andReturn();

        NumberReceiverResultDto receiverResult = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), NumberReceiverResultDto.class);

        //step 2: system generates winning numbers
        //given
        Instant drawDate = receiverResult.ticket().drawDate();
        //when
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() ->{
                    try {
                        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
                        return winningNumbersDto.numbers().size() == 6;
                    }catch (WinningNumbersNotFoundException exception){
                    return false;
                    }
                });
        //then


        //step 3: system is checking results
        //given
        //when
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() ->
                        resultCheckerFacade.isSystemGeneratedResults(drawDate)
                );
        //then

        // step 3: user check results and receives data that he won
        //given
        //when
        //then
    }

}
