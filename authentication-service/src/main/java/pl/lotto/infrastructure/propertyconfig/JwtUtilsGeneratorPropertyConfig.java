package pl.lotto.infrastructure.propertyconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import pl.lotto.jwtgenerator.JwtUtilsPropertyConfigurable;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtUtilsGeneratorPropertyConfig implements JwtUtilsPropertyConfigurable {

    private String secret;
    private long expirationAccess;
    private long expirationRefresh;
    private Expiration expiration;

    public Expiration getExpiration() {
        return expiration;
    }

    public void setExpiration(Expiration expiration) {
        this.expiration = expiration;
    }

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
        if (expirationAccess != 0) {
            return expirationAccess;
        }
        return expiration.minutes() * expiration.seconds() * expiration.millis();
    }

    @Override
    public void setAccessTokenExpiration(long expirationAccess) {
        this.expirationAccess = expirationAccess;
    }

    @Override
    public long getRefreshTokenExpiration() {
        if (expirationRefresh != 0) {
            return expirationAccess;
        }
        return expiration.minutes() * expiration.seconds() * expiration.millis() * expiration.refreshOffset();
    }

    @Override
    public void setRefreshTokenExpiration(long expirationRefresh) {
        this.expirationRefresh = expirationRefresh;
    }

    record Expiration(long minutes, long seconds, long millis, long refreshOffset) {

    }
}
