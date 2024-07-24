package pl.lotto.authfilter;

public interface JwtValidatorConfigurable {

    String getSecret();

    void setSecret(String secret);

    long getAccessTokenExpiration();

    void setAccessTokenExpiration(long accessExpiration);

    long getRefreshTokenExpiration();

    void setRefreshTokenExpiration(long refreshExpiration);
}
