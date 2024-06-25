package pl.lotto.infrastructure.scheduler.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ResultCheckerClockConfig {

    @Bean(name = "resultCheckerScheduler")
    Clock clock() {
        return Clock.systemUTC();
    }
}
