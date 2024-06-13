package pl.lotto.drawdategenerator;

import lombok.AllArgsConstructor;
import pl.lotto.drawdategenerator.dto.DrawDateDto;

import java.time.LocalDateTime;

@AllArgsConstructor
class DrawDateGeneratorFacadeImpl implements DrawDateGeneratorFacade {

    DateGenerator dateGenerator;

    @Override
    public DrawDateDto getNextDrawDate(LocalDateTime ticketCreatedAt) {
        LocalDateTime drawDateForTicket = dateGenerator.generateDrawDate(ticketCreatedAt);
        return new DrawDateDto(drawDateForTicket);
    }
}
