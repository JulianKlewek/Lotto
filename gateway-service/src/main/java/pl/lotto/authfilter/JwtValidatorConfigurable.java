package pl.lotto.authfilter;

public interface JwtValidatorConfigurable {

    String getSecret();

    void setSecret(String secret);

}
