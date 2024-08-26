package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> database = new ConcurrentHashMap<>();
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

    @Override
    public User findById(Long id) {
        return database.values().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    public List<User> saveAll(List<User> users) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getId() == null) {
                user.setId(userId.getAndIncrement());
            }
            database.put(user.getId(), user);
            result.add(user);
        }
        return result;
    }

}
