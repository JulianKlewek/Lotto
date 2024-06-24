package pl.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.infrastructure.controller.error.WinningNumbersGeneratorException;
import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponseDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultchecker.dto.WinningTicketDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("{\"inputNumbers\":" + typedNumbers + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        MvcResult mvcInputNumbersResult = perform.andExpectAll(
                        status().isOk(),
                        jsonPath("$.status").value("success"),
                        jsonPath("$.errorsList", hasSize(0)),
                        jsonPath("$.ticket.hash", matchesPattern(UUID_REGEX)),
                        jsonPath("$.ticket.numbers", contains(typedNumbers.toArray())),
                        jsonPath("$.ticket.drawDate", equalTo(expectedDrawDate)),
                        jsonPath("$.ticket.message", equalTo((null))))
                .andReturn();
        NumberReceiverResultDto receiverResult = objectMapper.readValue(
                mvcInputNumbersResult.getResponse().getContentAsString(), NumberReceiverResultDto.class);

        //step 2: system generates winning numbers and returns winning numbers for given date
        //given
        Instant drawDate = receiverResult.ticket().drawDate();
        String hash = receiverResult.ticket().hash();
        WinningTicketDto winningTicket = WinningTicketDto.builder()
                .hash(hash)
                .userNumbers(List.of(1, 2, 3, 4, 5, 6))
                .lotteryNumber(1L)
                .amountOfCorrectNumbers(6)
                .drawDate(drawDate)
                .build();
        WinningTicketsDto winningTicketsResponse = WinningTicketsDto.builder()
                .winningTickets(List.of(winningTicket))
                .build();
        //when & then
        StringValuePattern requestBodyPattern = new ContainsPattern(".*");

        WinningNumbersResponseDto a = WinningNumbersResponseDto.builder()
                .numbers(List.of(1,2,3,4,5,6))
                .lotteryNumber(1L)
                .drawDate(drawDate)
                .build();
        String responseBody = objectMapper.writeValueAsString(a);

//        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/winning-numbers/2024-06-14T20:00:00Z"))
        wireMockServer.stubFor(WireMock.get(urlPathTemplate("/winning-numbers/{drawDate}"))
//                        .withPathParam("drawDate", WireMock.equalTo("2024-06-14T20:00:00Z"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "*/*")
                        .withBody(responseBody)));


        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() -> {
                    try {
                        WinningNumbersResponseDto numbersForDate = winningNumbersPort.getWinningNumbersForDate(drawDate);
                        return numbersForDate.numbers().size() == 6;
                    } catch (WinningNumbersGeneratorException exception) {
                        return false;
                    }
                });

        WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo("http://numbersgeneratorservice/winning-numbers/2024-06-14T20:00:00Z"))
                .withHeader("Content-Type", WireMock.equalTo("*/*")));

        // step 3: user check results and receives data that he won
        //given & when
        String ticketId = receiverResult.ticket().hash();
        String wonNotReceived = "Congratulations you have won, you can receive reward.";
        ResultActions performResults = mockMvc.perform(get("/getResult/" + ticketId)
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
