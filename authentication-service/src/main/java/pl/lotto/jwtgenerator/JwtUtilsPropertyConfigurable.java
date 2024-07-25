package pl.lotto.jwtgenerator;

public interface JwtUtilsPropertyConfigurable {

    String getSecret();

    void setSecret(String secret);

    long getAccessTokenExpiration();

    void setAccessTokenExpiration(long accessExpiration);

    long getRefreshTokenExpiration();

    void setRefreshTokenExpiration(long refreshExpiration);

}
