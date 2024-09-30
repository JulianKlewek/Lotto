package pl.lotto.resultannouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.ResultCheckerFacadeImpl;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.time.*;

import static org.mockito.Mockito.mock;

abstract class ResultAnnouncerFacadeTestConfig implements SampleData {


    protected ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacadeImpl.class);
    protected WinningNumbersPort winningNumbersPort = mock(WinningNumbersPort.class);
    protected DrawDateGeneratorFacade drawDateGeneratorFacade = mock(DrawDateGeneratorFacade.class);
    protected ResultAnnouncerConfigurable resultAnnouncerConfigurable = new ResultAnnouncerPropertyConfigTest(
            winReceivedMsg, winNotReceivedMsg, loseMsg);
    protected ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().createResultAnnouncerFacadeForTests(
            resultCheckerFacade, resultAnnouncerConfigurable, winningNumbersPort, drawDateGeneratorFacade, clock());

    @Bean("resultAnnouncerClock")
    @Primary
    Clock clock() {
        LocalDateTime now = LocalDateTime.of(2024, Month.JUNE, 14, 21, 0);
        return Clock.fixed(now.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }
}
