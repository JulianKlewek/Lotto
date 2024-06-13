package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;
import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
class DrawDateGeneratorFacadeImpl implements DrawDateGeneratorFacade {

    DateGenerator dateGenerator;

    @Override
    public DrawDateDto getNextDrawDate(ZonedDateTime ticketCreatedAt) {
        ZonedDateTime drawDateForTicket = dateGenerator.generateDrawDate(ticketCreatedAt);
        return new DrawDateDto(drawDateForTicket);
    }
}
