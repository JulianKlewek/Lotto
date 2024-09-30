package pl.lotto.resultannouncer;

import pl.lotto.infrastructure.winningnumbersservice.dto.WinningNumbersResponse;
import pl.lotto.resultannouncer.dto.ResultResponse;
import pl.lotto.resultannouncer.dto.WinningResultsResponse;

import java.time.Instant;

public interface ResultAnnouncerFacade {

    ResultResponse findResultsForId(String uuid);

    WinningResultsResponse getLotteryResultsForDate(Instant drawDate);

    WinningNumbersResponse getLatestLotteryResults();
}
