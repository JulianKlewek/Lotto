package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.resultannouncer.dto.AnnouncerWinningResultsResponseDto;

import java.time.Instant;

public interface ResultAnnouncerFacade {

    AnnouncerResponseDto findResultsForId(String uuid);

    AnnouncerWinningResultsResponseDto getLotteryResultsForDate(Instant drawDate);
}
