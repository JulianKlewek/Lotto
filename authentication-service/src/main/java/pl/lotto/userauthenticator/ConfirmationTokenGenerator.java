package pl.lotto.userauthenticator;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.lotto.userauthenticator.entity.ConfirmationToken;
import pl.lotto.userauthenticator.entity.User;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Log4j2
@Service
class ConfirmationTokenGenerator {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final Clock clock;

    public ConfirmationTokenGenerator(ConfirmationTokenRepository confirmationTokenRepository,
                                      @Qualifier("confirmationTokenClock") Clock clock) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.clock = clock;
    }

    public ConfirmationToken generate(User user) {
        String token;
        do {
            log.info("Generating confirmation token for user [{}].", user.getUsername());
            token = UUID.randomUUID().toString();
        } while (confirmationTokenRepository.existsByToken(token));
        Instant now = Instant.now(clock);
        return new ConfirmationToken(token, now, user);
    }
}
