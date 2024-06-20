package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.AnnouncerResponseDto;

public interface ResultAnnouncerFacade {

    AnnouncerResponseDto findResultsForId(String uuid);
}
