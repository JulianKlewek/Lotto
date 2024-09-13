package pl.lotto.mailservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.lotto.mailservice.dto.EmailDetails;

@Log4j2
@Component
class EmailDetailsAssigner {

    private final BodyGenerator bodyGenerator;

    public EmailDetailsAssigner(BodyGenerator bodyGenerator) {
        this.bodyGenerator = bodyGenerator;
    }

    public EmailDetails assign(EmailType type, EmailRecipient properties) {
        log.info("Assigning details for mail type: [{}]", type);
        return switch (type) {
            case CONFIRMATION_MAIL -> {
                AccountCreatedProperties confirmationBodyDetails = (AccountCreatedProperties) properties;
                String body = bodyGenerator.generateConfirmationEmail(confirmationBodyDetails);
                yield new EmailDetails(EmailType.CONFIRMATION_MAIL.subject, body, AttachmentPathProvider.NO_ATTACHMENT);
            }
            case NEWSLETTER_MAIL -> {
                String body = bodyGenerator.generateNewsletterEmail(properties);
                yield new EmailDetails(EmailType.CONFIRMATION_MAIL.subject, body, AttachmentPathProvider.NO_ATTACHMENT);
            }
        };
    }

}
