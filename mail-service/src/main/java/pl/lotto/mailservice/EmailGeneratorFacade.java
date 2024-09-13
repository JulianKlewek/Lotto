package pl.lotto.mailservice;

public interface EmailGeneratorFacade {

    void generateDetails(EmailType emailType, EmailRecipient details);
}
