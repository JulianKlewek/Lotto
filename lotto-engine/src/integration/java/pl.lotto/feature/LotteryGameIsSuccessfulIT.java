package pl.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.numberreceiver.dto.NumberReceiverResult;
import pl.lotto.resultchecker.dto.WinningTickets;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

class LotteryGameIsSuccessfulIT extends BaseIntegrationTest {

    @Test
    @DisplayName("should user win when he inputs 6 winning numbers and retrieves results after the draw date")
    void happyPath_shouldUserInputNumbers_andWinSixNumberRewardNotReceived() throws Exception {
        //step 1: user types six number
        //given
        List<Integer> typedNumbers = List.of(1, 2, 3, 4, 5, 6);
        String expectedDrawDate = "2024-06-14T20:00:00Z";
        //when
        ResultActions perform = mockMvc.perform(post("/lottery/input-numbers")
                        .content("{\"inputNumbers\":" + typedNumbers + "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());
        //then
        MvcResult mvcInputNumbersResult = perform.andExpectAll(
                        status().isCreated(),
                        jsonPath("$.status").value("success"),
                        jsonPath("$.errorsList", hasSize(0)),
                        jsonPath("$.ticket.hash", matchesPattern(UUID_REGEX)),
                        jsonPath("$.ticket.numbers", contains(typedNumbers.toArray())),
                        jsonPath("$.ticket.drawDate", equalTo(expectedDrawDate)),
                        jsonPath("$.ticket.message", equalTo((null))))
                .andReturn();
        NumberReceiverResult receiverResult = objectMapper.readValue(
                mvcInputNumbersResult.getResponse().getContentAsString(), NumberReceiverResult.class);
        //step 2: system generates winning numbers and returns winning numbers for given date
        //given
        Instant drawDate = receiverResult.ticket().drawDate();
        WinningNumbersResponse winningNumbersResponse = WinningNumbersResponse.builder()
                .numbers(List.of(1, 2, 3, 4, 5, 6))
                .lotteryNumber(1L)
                .drawDate(drawDate)
                .build();
        String responseBody = objectMapper.writeValueAsString(winningNumbersResponse);
        //when & then
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathTemplate("/winning-numbers/{drawDate}"))
                .withPathParam("drawDate", WireMock.equalTo(encode("2024-06-14T20:00:00Z", UTF_8)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "*/*")
                        .withBody(responseBody)));

        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() -> {
                    WinningTickets tickets = resultCheckerFacade.checkAllWinningTicketsForGivenDrawDate(drawDate);
                    return !tickets.winningTickets().isEmpty();
                });

        // step 3: user check results and receives data that he won
        //given & when
        String ticketId = receiverResult.ticket().hash();
        String wonNotReceived = "Congratulations you have won, you can receive reward.";
        ResultActions performResults = mockMvc.perform(get("/result/get-result/" + ticketId)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        performResults.andExpectAll(
                        status().isOk(),
                        jsonPath("$.message").value(wonNotReceived),
                        jsonPath("$.ticket.amountOfCorrectNumbers", equalTo(6)),
                        jsonPath("$.ticket.hash", equalTo(ticketId)),
                        jsonPath("$.ticket.drawDate", equalTo(expectedDrawDate)))
                .andReturn();
    }

}
