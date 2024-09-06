package pl.lotto.userauthenticator;

import pl.lotto.userauthenticator.dto.EmailConfirmationResponse;
import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;

public interface UserAuthFacade {

    UserRegisterResponse register(UserRegisterRequest user);

    UserLoginResponse login(UserDetailsImpl userDetails);

    EmailConfirmationResponse confirmAccount(String token);
}
