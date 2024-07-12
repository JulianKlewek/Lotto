package pl.lotto.userauth;

import pl.lotto.userauthenticator.UserRepository;

class UserAuthTestConfig {

    UserRepository userRepository = new UserRepositoryTestImpl();
}
