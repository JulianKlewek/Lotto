package pl.lotto.infrastructure.propertyconfig.resultannouncer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultannouncer.ResultAnnouncerConfigurable;

@Configuration
@ConfigurationProperties(prefix = "lotto.result-announcer")
class ResultAnnouncerPropertyConfig implements ResultAnnouncerConfigurable {

    private String winMessageReceived;
    private String winMessageNotReceived;
    private String loseMessage;

    @Override
    public String getWinMessageReceived() {
        return winMessageReceived;
    }

    @Override
    public void setWinMessageReceived(String winMessageReceived) {
        this.winMessageReceived = winMessageReceived;
    }

    @Override
    public String getWinMessageNotReceived() {
        return winMessageNotReceived;
    }

    @Override
    public void setWinMessageNotReceived(String winMessageNotReceived) {
        this.winMessageNotReceived = winMessageNotReceived;
    }

    @Override
    public String getLoseMessage() {
        return this.loseMessage;
    }

    @Override
    public void setLoseMessage(String loseMessage) {
        this.loseMessage = loseMessage;
    }
}
