package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;
import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.Instant;

@AllArgsConstructor
class DrawDateGeneratorFacadeImpl implements DrawDateGeneratorFacade {

    DateGenerator dateGenerator;

    @Override
    public DrawDateDto getNextDrawDate(Instant ticketCreatedAt) {
        Instant drawDateForTicket = dateGenerator.generateDrawDate(ticketCreatedAt);
        return new DrawDateDto(drawDateForTicket);
    }
}
