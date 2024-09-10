package pl.lotto.userauthenticator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.lotto.userauthenticator.entity.ConfirmationToken;
import pl.lotto.userauthenticator.entity.Role;
import pl.lotto.userauthenticator.entity.User;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDateTime.of;

@Configuration
class UserAuthTestConfig {

    UserRepository userRepository = new InMemoryUserRepository();
    RoleRepository roleRepository = new InMemoryRoleRepository();
    ConfirmationTokenRepository confirmationTokenRepository = new InMemoryConfirmationTokenRepository();

    @Bean("confirmationTokenClock")
    @Primary
    Clock clock() {
        LocalDateTime today = LocalDateTime.of(2024, Month.AUGUST, 20, 20, 0, 0);
        return Clock.fixed(today.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    private final User userWithConfirmationToken = User.builder()
            .id(-2L)
            .username("expectedNonRegisteredUsername")
            .email("expectedNonRegisteredUsername@gmail.com")
            .password("Password")
            .build();

    private final User userWithConfirmationToken1 = User.builder()
            .id(-3L)
            .username("expectedNonRegisteredUsername1")
            .email("expectedNonRegisteredUsername1@gmail.com")
            .password("Password")
            .build();

    @BeforeEach
    public void beforeEach() {
        prepareUserRepository();
        prepareRoleRepository();
        prepareConfirmationTokenRepository();
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
                .username("existingUsername")
                .email("existingEmail@gmail.com")
                .password("Password")
                .build();
        userRepository.saveAll(List.of(defaultUser, userWithConfirmationToken, userWithConfirmationToken1));
    }

    private void prepareConfirmationTokenRepository() {
        confirmationTokenRepository.deleteAll();
        LocalDateTime localDateTime = of(2024, Month.AUGUST, 19, 20, 0);
        Instant expiryAfterClock = localDateTime.toInstant(ZoneOffset.UTC)
                .plus(25, ChronoUnit.HOURS);
        Instant expiryBeforeClock = localDateTime.toInstant(ZoneOffset.UTC)
                .plus(23, ChronoUnit.HOURS);
        ConfirmationToken tokenExpiryAfterClock = ConfirmationToken.builder()
                .token("9f30fefc-5971-4e55-b6f8-9fd8e44e7c5f")
                .createdAt(expiryAfterClock)
                .expiryAt(expiryAfterClock)
                .user(userWithConfirmationToken)
                .build();
        ConfirmationToken tokenExpiryBeforeClock = ConfirmationToken.builder()
                .token("9f30feff-5971-4e55-b6f8-9fd8e44e7c5f")
                .createdAt(expiryBeforeClock)
                .expiryAt(expiryBeforeClock)
                .user(userWithConfirmationToken1)
                .build();
        List<ConfirmationToken> tokens = List.of(tokenExpiryAfterClock, tokenExpiryBeforeClock);
        confirmationTokenRepository.saveAll(tokens);
    }
}
