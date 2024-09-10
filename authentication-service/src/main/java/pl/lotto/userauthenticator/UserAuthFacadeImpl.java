package pl.lotto.userauthenticator;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pl.lotto.infrastructure.emailsenderservice.dto.ConfTokenEmailEvent;
import pl.lotto.jwtgenerator.JwtGeneratorFacade;
import pl.lotto.jwtgenerator.dto.JwtResponse;
import pl.lotto.jwtgenerator.dto.UserTokenRequest;
import pl.lotto.userauthenticator.dto.*;
import pl.lotto.userauthenticator.entity.ConfirmationToken;
import pl.lotto.userauthenticator.entity.Role;
import pl.lotto.userauthenticator.entity.User;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Set;

import static pl.lotto.userauthenticator.PasswordCleaner.cleanPassword;
import static pl.lotto.userauthenticator.UserMapper.entityToResponse;
import static pl.lotto.userauthenticator.UserMapper.userDetailsToInfoResponse;

@Log4j2
@RequiredArgsConstructor
class UserAuthFacadeImpl implements UserAuthFacade {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGeneratorFacade jwtGeneratorFacade;
    private final ConfirmationTokenGenerator confirmationTokenGenerator;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserAccountEnabler userAccountEnabler;
    private final EmailSenderPort emailSenderPort;

    @Override
    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        char[] passArray = request.password();
        if (emailExists(request.email())) {
            cleanPassword(passArray);
            log.info("User [{}] register failed, email already exists: {}", request.username(), request.email());
            throw new UserAlreadyExistsException("Email already exists: " + request.email());
        }
        if (usernameExists(request.username())) {
            log.info("User [{}] register failed, username already exists.", request.username());
            cleanPassword(passArray);
            throw new UserAlreadyExistsException("Username already exists: " + request.username());
        }
        Role userRole = roleRepository.findByName(UserRole.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        CharSequence passCharSequence = CharBuffer.wrap(passArray);
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(passCharSequence))
                .roles(roles)
                .build();
        User savedUser = userRepository.save(user);
        log.info("Created account for username: [{}], with id: [{}]", savedUser.getUsername(), savedUser.getId());
        ConfirmationToken confirmationToken = confirmationTokenGenerator.generate(savedUser);
        ConfirmationToken savedConfToken = confirmationTokenRepository.save(confirmationToken);
        log.info("Saved confirmation token: [{}] for username: [{}]", savedConfToken.getToken(), savedUser.getUsername());
        ConfTokenEmailEvent emailRequest = sendConfirmationEmail(savedUser, savedConfToken);
        log.info("Sent email message: [{}]", emailRequest);
        cleanPassword(passArray);
        return entityToResponse(savedUser);
    }

    private ConfTokenEmailEvent sendConfirmationEmail(User savedUser, ConfirmationToken savedConfToken) {
        ConfTokenEmailEvent emailRequest = new ConfTokenEmailEvent(savedUser.getEmail(),
                savedConfToken.getToken(),
                savedConfToken.getExpiryAt());
        emailSenderPort.sendConfirmationEmail(emailRequest);
        return emailRequest;
    }


    @Override
    public UserLoginResponse login(UserDetailsImpl userDetails) {
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

    @Override
    public EmailConfirmationResponse confirmAccount(String token) {
        log.debug("Processing email confirmation token [{}]", token);
        ConfirmationToken confToken = confirmationTokenRepository.findByToken(token);
        ConfirmationResult confResult = userAccountEnabler.enable(confToken);
        log.info("Deleting confirmation token from database [{}]", confToken.getToken());
        confirmationTokenRepository.delete(confToken);
        String username = confToken.getUser().getUsername();
        return new EmailConfirmationResponse(username, confResult.status(), confResult.errors());
    }

    private boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
