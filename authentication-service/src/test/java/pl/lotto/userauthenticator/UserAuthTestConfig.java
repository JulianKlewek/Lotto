package pl.lotto.userauthenticator;

import org.junit.jupiter.api.BeforeEach;
import pl.lotto.userauthenticator.entity.User;

class UserAuthTestConfig {

    UserRepository userRepository = new UserRepositoryTestImpl();

    @BeforeEach
    public void beforeEach(){
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
