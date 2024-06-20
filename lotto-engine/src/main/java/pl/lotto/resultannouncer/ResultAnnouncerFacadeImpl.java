package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;
import pl.lotto.resultchecker.dto.WinningNumbersResultsDto;

import java.time.Instant;

import static pl.lotto.resultannouncer.AnnouncerResponseMapper.toDto;
import static pl.lotto.resultannouncer.WinningNumbersAnnouncerMapper.*;

@AllArgsConstructor
class ResultAnnouncerFacadeImpl implements ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultMessageGenerator messageGenerator;

    @Override
    public AnnouncerResponseDto findResultsForId(String uuid) {
        TicketResultResponseDto ticketResult = resultCheckerFacade.isSpecificTicketWon(uuid);
        String resultMessage = messageGenerator.generateResultMessage(ticketResult.status());
        return toDto(ticketResult, resultMessage);
    }

    @Override
    public AnnouncerWinningResultsResponseDto getLotteryResultsForDate(Instant drawDate) {
        WinningNumbersResultsDto winningNumbersResults = resultCheckerFacade.findWinningNumbersForLottery(drawDate);
        return resultsToResponse(winningNumbersResults);
    }
}
