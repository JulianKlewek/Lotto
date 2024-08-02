package pl.lotto.userauthenticator;

import org.junit.jupiter.api.BeforeEach;
import pl.lotto.userauthenticator.entity.Role;
import pl.lotto.userauthenticator.entity.User;

import java.util.List;

class UserAuthTestConfig {

    UserRepository userRepository = new UserRepositoryTestImpl();
    RoleRepository roleRepository = new RoleRepositoryTestImpl();

    @BeforeEach
    public void beforeEach() {
        prepareUserRepository();
        prepareRoleRepository();
    }

    private void prepareRoleRepository() {
        roleRepository.deleteAll();
        Role userRole = Role.builder()
                .name(UserRole.ROLE_USER)
                .build();
        Role moderatorRole = Role.builder()
                .name(UserRole.ROLE_MODERATOR)
                .build();
        Role adminRole = Role.builder()
                .name(UserRole.ROLE_ADMIN)
                .build();
        List<Role> roles = List.of(userRole, moderatorRole, adminRole);
        roleRepository.saveAll(roles);
    }

    private void prepareUserRepository() {
        userRepository.deleteAll();
        User defaultUser = User.builder()
                .id(-1L)
                .username("defaultusername")
                .email("defaultemail@gmail.com")
                .password("Password")
                .build();
        userRepository.save(defaultUser);
    }

}
