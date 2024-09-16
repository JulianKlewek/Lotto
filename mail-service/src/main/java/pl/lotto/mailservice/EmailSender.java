package pl.lotto.mailservice;

public interface EmailSender {

    void send(String recipient, String subject, String body);

    void send(String recipient, String subject, String body, String pathToAttachment);
}
