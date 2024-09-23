package pl.lotto.mailservice;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Class changes email sender behavior by changing recipient address to sender address.
 */
@Log4j2
@Component
@ConditionalOnProperty(name = "email.sender.enabled", havingValue = "false", matchIfMissing = true)
class FakeEmailSender implements EmailSender {

    private final JavaMailSender mailSender;
    private final EmailSenderConfigurable senderConfigurable;

    public FakeEmailSender(JavaMailSender mailSender, EmailSenderConfigurable senderConfigurable) {
        this.mailSender = mailSender;
        this.senderConfigurable = senderConfigurable;
    }

    @Async
    @Override
    public void send(String recipient, String subject, String body) {
        String sender = senderConfigurable.getAddress();
        log.info("---FakeEmailSender--- Changing recipient from: [{}] to: [{}]", recipient, sender);
        recipient = sender;
        log.info("---FakeEmailSender--- Recipient: [{}], subject: [{}], body: [{}]", recipient, subject, body);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException exc) {
            log.error("Mail sending failure: " + exc.getMessage());
        }
    }

    @Async
    @Override
    public void send(String recipient, String subject, String body, String pathToAttachment) {
        String sender = senderConfigurable.getAddress();
        log.info("---FakeEmailSender--- Changing recipient from: [{}] to: [{}]", recipient, sender);
        recipient = sender;
        log.info("---FakeEmailSender--- Recipient: [{}], subject: [{}], body: [{}]", recipient, subject, body);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
            mailSender.send(message);
        } catch (MessagingException exc) {
            log.error("Mail sending failure: " + exc.getMessage());
        }
    }
}
