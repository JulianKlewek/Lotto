package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryUserRepository implements UserRepository {

    protected static final Map<Long, User> database = new ConcurrentHashMap<>();
    private static final AtomicLong userId = new AtomicLong(0L);

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(userId.getAndIncrement());
        }
        database.put(user.getId(), user);
        return database.get(user.getId());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return database.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public boolean existsByUsername(String username) {
        return database.values()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public boolean existsByEmail(String email) {
        return database.values()
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

}
