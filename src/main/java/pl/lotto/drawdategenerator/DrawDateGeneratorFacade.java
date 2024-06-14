package pl.lotto.drawdategenerator;

import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.Instant;

public interface DrawDateGeneratorFacade {

    DrawDateDto getNextDrawDate(Instant ticketCreatedAt);
}
