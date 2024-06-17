package pl.lotto.resultannouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Configuration
public class ResultAnnouncerConfiguration {

    @Bean
    public ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade,
                                                       ResultAnnouncerConfigurable announcerConfigurable) {
        ResultMessageGenerator messageGenerator = new ResultMessageGenerator(announcerConfigurable);
        return new ResultAnnouncerFacadeImpl(resultCheckerFacade, messageGenerator);
    }

    public ResultAnnouncerFacade resultAnnouncerFacadeForTests(ResultCheckerFacade resultCheckerFacade,
                                                               ResultAnnouncerConfigurable announcerConfigurable) {
        return resultAnnouncerFacade(resultCheckerFacade, announcerConfigurable);
    }
}
