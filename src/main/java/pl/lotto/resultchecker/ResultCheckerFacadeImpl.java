package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.WinningNumbersDto;
import pl.lotto.resultchecker.dto.WinningTicketsDto;

import java.time.Instant;

@AllArgsConstructor
public class ResultCheckerFacadeImpl implements ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumbersGeneratorFacade numbersGeneratorFacade;

    @Override
    public WinningTicketsDto getAllWinningTicketsForGivenDrawDate(Instant drawDate) {
        UserTicketsDto userTicketsDto = numberReceiverFacade.usersNumbers(drawDate);
        WinningNumbersDto winningNumbers = numbersGeneratorFacade.getWinningNumbersForDate(drawDate);
        return new WinningTicketsDto();
    }
}
