package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.lotto.drawdategenerator.dto.DrawDate;

import java.time.Instant;

@Log4j2
@AllArgsConstructor
class DrawDateGeneratorFacadeImpl implements DrawDateGeneratorFacade {

    private final DateGenerator dateGenerator;

    @Override
    public DrawDate getNextDrawDate(Instant ticketCreatedAt) {
        Instant drawDateForTicket = dateGenerator.generateDrawDate(ticketCreatedAt);
        log.debug("Generated date [{}] for creation time: [{}]", drawDateForTicket, ticketCreatedAt);
        return new DrawDate(drawDateForTicket);
    }
}
