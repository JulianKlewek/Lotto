package pl.lotto.drawdategenerator;

import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.LocalDateTime;

public interface DrawDateGeneratorFacade {

    DrawDateDto getNextDrawDate(LocalDateTime ticketCreatedAt);
}
