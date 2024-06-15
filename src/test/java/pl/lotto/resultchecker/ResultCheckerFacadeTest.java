package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

import static org.mockito.Mockito.*;

class ResultCheckerFacadeTest extends ResultCheckerFacadeTestConfig {

    @Test
    void should_return() {
        //given
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        NumbersGeneratorFacade numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        ResultCheckerFacadeImpl resultCheckerFacade = new ResultCheckerFacadeConfiguration().resultCheckerFacadeForTests(
                numberReceiverFacade, numbersGeneratorFacade);
        //when
//        when(numberReceiverFacade.inputNumbers(any())).thenReturn();
//        when(drawDateGeneratorFacade.getNextDrawDate(any())).thenReturn();
//        when(numbersGeneratorFacade.generateWinningNumbers()).thenReturn();
//        resultCheckerFacade.checkWinners();
        //then
    }
}