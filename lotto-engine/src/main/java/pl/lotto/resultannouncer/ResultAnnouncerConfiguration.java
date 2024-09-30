package pl.lotto.resultannouncer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.drawdategenerator.DrawDateGeneratorFacade;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.WinningNumbersPort;

import java.time.Clock;

@Configuration
public class ResultAnnouncerConfiguration {

    @Bean
    public ResultAnnouncerFacade createResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade,
                                                             ResultAnnouncerConfigurable announcerConfigurable,
                                                             WinningNumbersPort winningNumbersPort,
                                                             DrawDateGeneratorFacade drawDateGeneratorFacade,
                                                             @Qualifier("resultAnnouncerClock") Clock clock) {
        ResultMessageGenerator messageGenerator = new ResultMessageGenerator(announcerConfigurable);
        LatestResultsService latestResultsService = new LatestResultsService(winningNumbersPort, drawDateGeneratorFacade, clock);
        return new ResultAnnouncerFacadeImpl(resultCheckerFacade,
                messageGenerator,
                latestResultsService);
    }

    public ResultAnnouncerFacade createResultAnnouncerFacadeForTests(ResultCheckerFacade resultCheckerFacade,
                                                                     ResultAnnouncerConfigurable announcerConfigurable,
                                                                     WinningNumbersPort winningNumbersPort,
                                                                     DrawDateGeneratorFacade drawDateGeneratorFacade,
                                                                     @Qualifier("resultAnnouncerClock") Clock clock) {
        return createResultAnnouncerFacade(resultCheckerFacade,
                announcerConfigurable,
                winningNumbersPort,
                drawDateGeneratorFacade,
                clock);
    }

    @Bean("resultAnnouncerClock")
    Clock clock() {
        return Clock.systemUTC();
    }
}
