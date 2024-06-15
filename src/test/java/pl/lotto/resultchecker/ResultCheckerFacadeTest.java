package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.UserTicketsDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

import java.util.List;

import static org.mockito.Mockito.*;

class ResultCheckerFacadeTest extends ResultCheckerFacadeTestConfig {

    @Test
    void should_return() {
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade);
//        when(numbersGeneratorFacade.generateWinningNumbers()).thenReturn();
//        resultCheckerFacade.getAllWinningTicketsForGivenDrawDate();
//        when(numberReceiverFacade.usersNumbers(any())).thenReturn(
//                new UserTicketsDto(List.of(
//                        new TicketDto("hash1", )
//                ))
//        );
        //when
        //then
    }
}