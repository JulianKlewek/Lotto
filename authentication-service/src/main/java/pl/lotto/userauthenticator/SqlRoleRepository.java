package pl.lotto.userauthenticator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lotto.userauthenticator.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
class SqlRoleRepository implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public SqlRoleRepository(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Role findByName(UserRole name) {
        return jpaRoleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role \"" + name + "\" does not exists in database."));
    }

    @Override
    public void deleteAll() {
        jpaRoleRepository.deleteAll();
    }

    @Override
    public Role save(Role role) {
        return jpaRoleRepository.save(role);
    }

    @Override
    public List<Role> saveAll(Iterable<Role> roles) {
        return jpaRoleRepository.saveAll(roles);
    }
}

@Repository
interface JpaRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRole name);
}