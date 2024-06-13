package pl.lotto.drawdategenerator;

import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface DrawDateGeneratorFacade {

    DrawDateDto getNextDrawDate(ZonedDateTime ticketCreatedAt);
}
