package pl.lotto.infrastructure.propertyconfig.resultannouncer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultannouncer.ResultAnnouncerConfigurable;

@Configuration
@ConfigurationProperties(prefix = "lotto.result-announcer")
class ResultAnnouncerPropertyConfig implements ResultAnnouncerConfigurable {

    private String winMessage;
    private String loseMessage;

    @Override
    public String getWinMessage() {
        return winMessage;
    }

    @Override
    public void setWinMessage(String winMessage) {
        this.winMessage = winMessage;
    }

    @Override
    public String getLoseMessage() {
        return null;
    }

    @Override
    public void setLoseMessage(String loseMessage) {
        this.loseMessage = loseMessage;
    }
}
