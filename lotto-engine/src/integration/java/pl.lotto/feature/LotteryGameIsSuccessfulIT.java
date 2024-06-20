//package pl.lotto.feature;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import pl.lotto.BaseIntegrationTest;
//import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
//import pl.lotto.numbersgenerator.WinningNumbersNotFoundException;
//import pl.lotto.numbersgenerator.dto.WinningNumbersResponseDto;
//import pl.lotto.resultchecker.dto.WinningTicketsDto;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import static org.awaitility.Awaitility.await;
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class LotteryGameIsSuccessfulIT extends BaseIntegrationTest {
//
//    @Test
//    @DisplayName("should user win when he inputs 6 winning numbers and retrieves results after the draw date")
//    void happyPath_shouldUserInputNumbers_andWinSixNumberRewardNotReceived() throws Exception {
//        //step 1: user types six number
//        //given
//        List<Integer> typedNumbers = List.of(1, 2, 3, 4, 5, 6);
//        String expectedDrawDate = "2024-06-14T20:00:00Z";
//        //when
//        ResultActions perform = mockMvc.perform(post("/inputNumbers")
//                .content("{\"inputNumbers\":" + typedNumbers + "}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE));
//        //then
//        MvcResult mvcInputNumbersResult = perform.andExpectAll(
//                        status().isOk(),
//                        jsonPath("$.status").value("success"),
//                        jsonPath("$.errorsList", hasSize(0)),
//                        jsonPath("$.ticket.hash", matchesPattern(UUID_REGEX)),
//                        jsonPath("$.ticket.numbers", contains(typedNumbers.toArray())),
//                        jsonPath("$.ticket.drawDate", equalTo(expectedDrawDate)),
//                        jsonPath("$.ticket.message", equalTo((null))))
//                .andReturn();
//        NumberReceiverResultDto receiverResult = objectMapper.readValue(
//                mvcInputNumbersResult.getResponse().getContentAsString(), NumberReceiverResultDto.class);
//
//        //step 2: system generates winning numbers
//        //given
//        Instant drawDate = receiverResult.ticket().drawDate();
//        //when & then
//        await().atMost(20, TimeUnit.SECONDS)
//                .pollInterval(1, TimeUnit.SECONDS)
//                .until(() -> {
//                    try {
//                        WinningNumbersResponseDto winningNumbersResponseDto = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
//                        return winningNumbersResponseDto.numbers().size() == 6;
//                    } catch (WinningNumbersNotFoundException exception) {
//                        return false;
//                    }
//                });
//
//        //step 3: system checks results
//        //given & when & then
//        await().atMost(20, TimeUnit.SECONDS)
//                .pollInterval(1, TimeUnit.SECONDS)
//                .until(() ->
//                {
//                    WinningTicketsDto winningTicketsDto = resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(drawDate);
//                    return !winningTicketsDto.winningTickets().isEmpty();
//                });
//
//        // step 3: user check results and receives data that he won
//        //given & when
//        String ticketId = receiverResult.ticket().hash();
//        String wonNotReceived = "Congratulations you have won, you can receive reward.";
//        ResultActions performResults = mockMvc.perform(get("/getResult/" + ticketId)
//                .contentType(MediaType.APPLICATION_JSON_VALUE));
//        //then
//        performResults.andExpectAll(
//                        status().isOk(),
//                        jsonPath("$.message").value(wonNotReceived),
//                        jsonPath("$.ticket.amountOfCorrectNumbers", equalTo(6)),
//                        jsonPath("$.ticket.hash", equalTo(ticketId)),
//                        jsonPath("$.ticket.drawDate", equalTo(expectedDrawDate)))
//                .andReturn();
//    }
//
//}
