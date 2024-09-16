package pl.lotto.mailservice;

public enum EmailType {

    CONFIRMATION_MAIL("Mail confirmation"),
    NEWSLETTER_MAIL("Newsletter lottery");

    final String subject;

    EmailType(String subject) {
        this.subject = subject;
    }
}
