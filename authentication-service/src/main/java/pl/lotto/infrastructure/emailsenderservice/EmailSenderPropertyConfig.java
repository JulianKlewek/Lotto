package pl.lotto.infrastructure.emailsenderservice;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "email.sender")
class EmailSenderPropertyConfig {

    private String queue;
    private String exchange;
    private RoutingKey routingKey;

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setRoutingKey(RoutingKey routingKey) {
        this.routingKey = routingKey;
    }

    record RoutingKey(String registration) {

    }
}
