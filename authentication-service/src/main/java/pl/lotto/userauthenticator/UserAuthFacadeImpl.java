package pl.lotto.userauthenticator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.lotto.jwtgenerator.JwtGeneratorFacade;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;
import pl.lotto.userauthenticator.dto.UserInfoResponse;
import pl.lotto.userauthenticator.dto.UserLoginResponse;
import pl.lotto.userauthenticator.dto.UserRegisterRequest;
import pl.lotto.userauthenticator.dto.UserRegisterResponse;
import pl.lotto.userauthenticator.entity.Role;
import pl.lotto.userauthenticator.entity.User;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Set;

import static pl.lotto.userauthenticator.PasswordCleaner.cleanPassword;
import static pl.lotto.userauthenticator.UserMapper.entityToResponse;
import static pl.lotto.userauthenticator.UserMapper.userDetailsToInfoResponse;

@RequiredArgsConstructor
class UserAuthFacadeImpl implements UserAuthFacade {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGeneratorFacade jwtGeneratorFacade;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        char[] passArray = request.password();
        if (userRepository.existsByEmail(request.email())) {
            cleanPassword(passArray);
            throw new UserAlreadyExistsException("Email already exists: " + request.email());
        }
        if (userRepository.existsByUsername(request.username())) {
            cleanPassword(passArray);
            throw new UserAlreadyExistsException("Username already exists: " + request.username());
        }
        Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        CharSequence passCharSequence = CharBuffer.wrap(passArray);
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(passCharSequence))
                .roles(roles)
                .build();
        User saved = userRepository.save(user);
        cleanPassword(passArray);
        return entityToResponse(saved);
    }

    @Override
    public UserLoginResponse prepareLoginResponse(UserDetailsImpl userDetails) {
        UserTokenRequest accessTokenRequest = UserTokenRequest.builder()
                .id(userDetails.getId())
                .tokenType("ACCESS")
                .build();
        JwtResponse accessTokenResponse = jwtGeneratorFacade.generateToken(accessTokenRequest);
        UserInfoResponse userInfoResponse = userDetailsToInfoResponse(userDetails);
        return UserLoginResponse.builder()
                .user(userInfoResponse)
                .accessToken(accessTokenResponse.token())
                .build();
    }
}
