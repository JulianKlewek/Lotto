package pl.lotto.mailservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.lotto.mailservice.dto.EmailDetails;

@Log4j2
@Service
class EmailGeneratorFacadeImpl implements EmailGeneratorFacade {

    private final EmailSender emailSender;
    private final EmailDetailsAssigner emailDetailsAssigner;

    public EmailGeneratorFacadeImpl(EmailSender emailSender, EmailDetailsAssigner emailDetailsAssigner) {
        this.emailSender = emailSender;
        this.emailDetailsAssigner = emailDetailsAssigner;
    }

    @Override
    public void generateDetails(EmailType emailType, EmailRecipient details) {
        log.info("Preparing mail data");
        String recipient = details.getRecipient();
        EmailDetails emailDetails = emailDetailsAssigner.assign(emailType, details);
        if (emailDetails.pathToAttachment().equals(AttachmentPathProvider.NO_ATTACHMENT)) {
            emailSender.send(recipient, emailDetails.subject(), emailDetails.body());
        } else {
            emailSender.send(recipient, emailDetails.subject(), emailDetails.body(), emailDetails.pathToAttachment());
        }
    }
}
