package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;
import java.time.ZonedDateTime;

@AllArgsConstructor
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @Override
    public WinningTicketsDto getAllWinningTicketsForGivenDrawDate(ZonedDateTime drawDate) {
        Instant givenDrawDate = drawDate.toInstant();
        UserTicketsDto userTicketsDto = numberReceiverFacade.usersNumbers(givenDrawDate);
        return new WinningTicketsDto();
    }
}
