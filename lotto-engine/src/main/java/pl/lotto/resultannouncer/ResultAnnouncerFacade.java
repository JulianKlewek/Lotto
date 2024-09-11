package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerResultResponse;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponse;

import java.time.Instant;

public interface ResultAnnouncerFacade {

    AnnouncerResultResponse findResultsForId(String uuid);

    AnnouncerWinningResultsResponse getLotteryResultsForDate(Instant drawDate);
}
