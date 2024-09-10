package pl.lotto.userauthenticator.dto;

import pl.lotto.userauthenticator.ConfirmationStatus;

import java.util.List;

public record EmailConfirmationResponse(String username, boolean success, List<String> errors) {

    public EmailConfirmationResponse(String username, ConfirmationStatus status, List<String> errors) {
        this(username, status.equals(ConfirmationStatus.SUCCESS), errors);
    }
}
