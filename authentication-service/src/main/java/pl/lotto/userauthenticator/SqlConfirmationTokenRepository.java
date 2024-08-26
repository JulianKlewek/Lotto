package pl.lotto.userauthenticator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lotto.userauthenticator.entity.ConfirmationToken;

import java.util.List;
import java.util.Optional;

@Repository
class SqlConfirmationTokenRepository implements ConfirmationTokenRepository {

    private final JpaConfirmationTokenRepository confirmationTokenRepository;

    public SqlConfirmationTokenRepository(JpaConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public boolean existsByToken(String token) {
        return confirmationTokenRepository.existsById(token);
    }

    @Override
    public ConfirmationToken save(ConfirmationToken token) {
        return confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ConfirmationTokenNotFoundException("Given token does not exists " + token));
    }

    @Override
    public void delete(ConfirmationToken confToken) {
        confirmationTokenRepository.delete(confToken);
    }

    @Override
    public void deleteAll() {
        confirmationTokenRepository.deleteAll();
    }

    @Override
    public List<ConfirmationToken> saveAll(List<ConfirmationToken> tokens) {
        return confirmationTokenRepository.saveAll(tokens);
    }
}

@Repository
interface JpaConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByToken(String token);
}
