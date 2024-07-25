package pl.lotto.infrastructure.propertyconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.lotto.authfilter.JwtValidatorConfigurable;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtValidatorGeneratorProperties implements JwtValidatorConfigurable {

    private String secret;

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
