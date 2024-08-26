package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.ConfirmationToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryConfirmationTokenRepository implements ConfirmationTokenRepository {

    private final Map<String, ConfirmationToken> database = new ConcurrentHashMap<>();

    @Override
    public boolean existsByToken(String token) {
        return database.values().stream()
                .anyMatch(dbToken -> dbToken.getToken().equals(token));
    }

    @Override
    public ConfirmationToken save(ConfirmationToken token) {
        database.put(token.getToken(), token);
        return database.get(token.getToken());
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return database.values().stream()
                .filter(t -> t.getToken().equals(token))
                .findFirst()
                .orElseThrow(() -> new ConfirmationTokenNotFoundException("Given token does not exists " + token));
    }

    @Override
    public void delete(ConfirmationToken confToken) {
        database.remove(confToken.getToken());
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public List<ConfirmationToken> saveAll(List<ConfirmationToken> tokens) {
        List<ConfirmationToken> savedTokens = new ArrayList<>();
        for (ConfirmationToken token : tokens) {
            database.put(token.getToken(), token);
            savedTokens.add(token);
        }
        return savedTokens;
    }
}
