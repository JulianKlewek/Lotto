package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.entity.Role;

import java.util.List;

public interface RoleRepository {

    Role findByName(UserRole name);

    void deleteAll();

    Role save(Role role);

    List<Role> saveAll(Iterable<Role> roles);
}
