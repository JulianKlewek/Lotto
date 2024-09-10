package pl.lotto.userauthenticator;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lotto.userauthenticator.dto.ConfirmationResult;
import pl.lotto.userauthenticator.entity.ConfirmationToken;
import pl.lotto.userauthenticator.entity.User;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
class UserAccountEnabler {
    private final UserRepository userRepository;
    private final Clock clock;

    public UserAccountEnabler(UserRepository userRepository, @Qualifier("confirmationTokenClock") Clock clock) {
        this.userRepository = userRepository;
        this.clock = clock;
    }

    @Transactional
    public ConfirmationResult enable(ConfirmationToken token) {
        Instant expiryAt = token.getExpiryAt();
        Instant now = clock.instant();
        if (now.isAfter(expiryAt)) {
            List<String> errors = new ArrayList<>();
            errors.add("Confirmation token expired");
            log.info("Confirmation token expired. Expiry date: [{}], current date: [{}]", expiryAt, clock.instant());
            return new ConfirmationResult(ConfirmationStatus.FAILED, errors);
        }
        Long userId = token.getUser().getId();
        User disabledUser = userRepository.findById(userId);
        log.info("Enabling user [{}] account", disabledUser.getUsername());
        disabledUser.setEnabled(true);
        return new ConfirmationResult(ConfirmationStatus.SUCCESS, new ArrayList<>());
    }
}
