package pl.lotto.mailservice;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Log4j2
@Component
@ConditionalOnProperty(name = "email.sender.enabled", havingValue = "true")
class RealEmailSender implements EmailSender {

    private final JavaMailSender mailSender;
    private final EmailSenderConfigurable senderConfigurable;

    public RealEmailSender(JavaMailSender mailSender, EmailSenderConfigurable senderConfigurable) {
        this.mailSender = mailSender;
        this.senderConfigurable = senderConfigurable;
    }

    @Override
    public void send(String recipient, String subject, String body) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String senderMail = senderConfigurable.getAddress();
            helper.setFrom(senderMail);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body);
            mailSender.send(message);
        } catch (MessagingException exc) {
            log.error("Mail sending failure: " + exc.getMessage());
        }
    }

    @Override
    public void send(String recipient, String subject, String body, String pathToAttachment) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String senderMail = senderConfigurable.getAddress();
            helper.setFrom(senderMail);
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