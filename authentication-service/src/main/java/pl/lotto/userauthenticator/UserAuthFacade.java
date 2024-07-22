package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

public interface UserAuthFacade {

    UserRegisterResponse register(UserRegisterRequest user);

    UserLoginResponse loginUser(UserDetailsImpl userDetails);
}
