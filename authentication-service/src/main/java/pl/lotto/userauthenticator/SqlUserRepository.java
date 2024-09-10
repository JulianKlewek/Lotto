package pl.lotto.userauthenticator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lotto.userauthenticator.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
class SqlUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public SqlUserRepository(JpaUserRepository userRepository) {
        this.jpaUserRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAll();
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found."));
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return jpaUserRepository.saveAll(users);
    }
}

@Repository
interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}