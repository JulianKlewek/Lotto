package pl.lotto.infrastructure.propertyconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.lotto.jwtgenerator.JwtUtilsPropertyConfigurable;

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtUtilsGeneratorPropertyConfig implements JwtUtilsPropertyConfigurable {

    private String secret;
    private long expirationAccess;
    private long expirationRefresh;

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public long getAccessTokenExpiration() {
        return expirationAccess;
    }

    @Override
    public void setAccessTokenExpiration(long expirationAccess) {
        this.expirationAccess = expirationAccess;
    }

    @Override
    public long getRefreshTokenExpiration() {
        return expirationRefresh;
    }

    @Override
    public void setRefreshTokenExpiration(long expirationRefresh) {
        this.expirationRefresh = expirationRefresh;
    }
}
