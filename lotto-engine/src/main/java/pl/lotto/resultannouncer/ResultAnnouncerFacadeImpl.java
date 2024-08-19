package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningNumbersResultsDto;

import java.time.Instant;

import static pl.lotto.resultannouncer.AnnouncerResponseMapper.toDto;
import static pl.lotto.resultannouncer.WinningNumbersAnnouncerMapper.resultsToResponse;

@AllArgsConstructor
@Log4j2
class ResultAnnouncerFacadeImpl implements ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultMessageGenerator messageGenerator;

    @Override
    public AnnouncerResponseDto findResultsForId(String uuid) {
        TicketResultResponseDto ticketResult = resultCheckerFacade.isSpecificTicketWon(uuid);
        String resultMessage = messageGenerator.generateResultMessage(ticketResult.status());
        log.info("Generating results for ticket with uuid: [{}] result: [{}]", uuid, ticketResult.status());
        return toDto(ticketResult, resultMessage);
    }

    @Override
    public AnnouncerWinningResultsResponseDto getLotteryResultsForDate(Instant drawDate) {
        log.debug("Generating response for date: [{}]", drawDate);
        WinningNumbersResultsDto winningNumbersResults = resultCheckerFacade.findWinningNumbersForLottery(drawDate);
        return resultsToResponse(winningNumbersResults);
    }
}
