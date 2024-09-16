package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.resultannouncer.dto.ResultResponse;
import pl.lotto.resultannouncer.dto.WinningResultsResponse;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.TicketResultResponse;
import pl.lotto.resultchecker.dto.WinningNumbersResults;

import java.time.Instant;

import static pl.lotto.resultannouncer.AnnouncerResponseMapper.toDto;
import static pl.lotto.resultannouncer.WinningNumbersAnnouncerMapper.resultsToResponse;

@AllArgsConstructor
@Log4j2
class ResultAnnouncerFacadeImpl implements ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultMessageGenerator messageGenerator;

    @Override
    public ResultResponse findResultsForId(String uuid) {
        TicketResultResponse ticketResult = resultCheckerFacade.isSpecificTicketWon(uuid);
        String resultMessage = messageGenerator.generateResultMessage(ticketResult.status());
        log.info("Generating results for ticket with uuid: [{}] result: [{}]", uuid, ticketResult.status());
        return toDto(ticketResult, resultMessage);
    }

    @Override
    public WinningResultsResponse getLotteryResultsForDate(Instant drawDate) {
        log.debug("Generating response for date: [{}]", drawDate);
        WinningNumbersResults winningNumbersResults = resultCheckerFacade.findWinningNumbersForLottery(drawDate);
        return resultsToResponse(winningNumbersResults);
    }
}
