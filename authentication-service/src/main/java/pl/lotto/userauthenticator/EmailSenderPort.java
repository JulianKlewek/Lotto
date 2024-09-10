package pl.lotto.userauthenticator;

import pl.lotto.infrastructure.emailsenderservice.dto.ConfTokenEmailEvent;

public interface EmailSenderPort {

    void sendConfirmationEmail(ConfTokenEmailEvent emailRequest);
}
