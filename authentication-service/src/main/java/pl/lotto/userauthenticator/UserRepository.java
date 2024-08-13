package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User save(User user);

    void deleteAll();
}
