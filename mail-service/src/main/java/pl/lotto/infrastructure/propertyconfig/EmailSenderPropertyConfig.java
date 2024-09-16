package pl.lotto.infrastructure.propertyconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import pl.lotto.mailservice.EmailSenderConfigurable;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "email.sender")
class EmailSenderPropertyConfig implements EmailSenderConfigurable {

    private String address;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String username) {
        this.address = username;
    }
}
