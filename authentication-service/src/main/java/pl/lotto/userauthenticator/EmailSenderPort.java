package pl.lotto.userauthenticator;

import pl.lotto.infrastructure.emailsenderservice.dto.AccountCreatedEvent;

public interface EmailSenderPort {

    void sendAccountCreatedEvent(AccountCreatedEvent emailRequest);
}
