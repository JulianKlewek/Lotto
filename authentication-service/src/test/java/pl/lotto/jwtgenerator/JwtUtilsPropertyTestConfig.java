package pl.lotto.jwtgenerator;

public class JwtUtilsPropertyTestConfig implements JwtUtilsPropertyConfigurable {

    private String secret;
    private long expirationAccess;
    private long expirationRefresh;

    public JwtUtilsPropertyTestConfig(String secret, long expirationAccess, long expirationRefresh) {
        this.secret = secret;
        this.expirationAccess = expirationAccess;
        this.expirationRefresh = expirationRefresh;
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
