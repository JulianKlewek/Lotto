package pl.lotto.drawdategenerator;

import pl.lotto.drawdategenerator.dto.DrawDate;

import java.time.Instant;

public interface DrawDateGeneratorFacade {

    DrawDate getNextDrawDate(Instant ticketCreatedAt);
}
