package pl.lotto.mailservice;

import java.time.Instant;

public interface AccountCreatedProperties extends EmailRecipient {

    String getConfirmationToken();

    Instant getExpirationTime();
}
