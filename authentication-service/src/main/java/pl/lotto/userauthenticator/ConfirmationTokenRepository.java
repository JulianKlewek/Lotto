package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.ConfirmationToken;

import java.util.List;

public interface ConfirmationTokenRepository {

    boolean existsByToken(String token);

    ConfirmationToken save(ConfirmationToken token);

    ConfirmationToken findByToken(String token);

    void delete(ConfirmationToken confToken);

    void deleteAll();

    List<ConfirmationToken> saveAll(List<ConfirmationToken> tokens);
}
