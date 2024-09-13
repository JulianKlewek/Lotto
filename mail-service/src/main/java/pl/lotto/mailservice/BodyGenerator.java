package pl.lotto.mailservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
class BodyGenerator {

    public String generateConfirmationEmail(AccountCreatedProperties details) {
        log.info("Generating confirmation email body for [{}]", details);
        return "confirmation email token: " + details.getConfirmationToken() + ", expiration: " + details.getExpirationTime();
    }

    public String generateNewsletterEmail(EmailRecipient properties) {
        return "newsletter";
    }
}
