package pl.lotto.mailservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@ConditionalOnProperty(name = "email.sender.enabled", havingValue = "false", matchIfMissing = true)
class FakeEmailSender implements EmailSender {

    private final EmailSenderConfigurable senderConfigurable;

    public FakeEmailSender(EmailSenderConfigurable senderConfigurable) {
        this.senderConfigurable = senderConfigurable;
    }

    @Override
    public void send(String recipient, String subject, String body) {
        String senderAddress = senderConfigurable.getAddress();
        log.info("---FakeEmailSender--- SenderUsername: [{}]", senderAddress);
        log.info("---FakeEmailSender--- Recipient: [{}], subject: [{}], body: [{}]", recipient, subject, body);
    }

    @Override
    public void send(String recipient, String subject, String body, String pathToAttachment) {
        String senderUsername = senderConfigurable.getAddress();
        log.info("---FakeEmailSender--- SenderUsername: [{}]", senderUsername);
        log.info("---FakeEmailSender--- Recipient: [{}], subject: [{}], body: [{}]", recipient, subject, body);
    }
}
