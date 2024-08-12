package pl.lotto.userauthenticator;

public class RoleDoesNotExistsException extends RuntimeException {

    public RoleDoesNotExistsException(String message) {
        super(message);
    }
}
