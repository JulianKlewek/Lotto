package pl.lotto.userauthenticator;

import pl.lotto.infrastructure.emailsenderservice.dto.ConfTokenEmailMessage;

public interface EmailSenderPort {

    void sendConfirmationEmail(ConfTokenEmailMessage emailRequest);
}
