package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.TicketResultResponseDto;

import static pl.lotto.resultannouncer.AnnouncerResponseMapper.*;

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
}
