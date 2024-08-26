package pl.lotto.userauthenticator;

public class ConfirmationTokenNotFoundException extends RuntimeException {
    public ConfirmationTokenNotFoundException(String message) {
        super(message);
    }
}
