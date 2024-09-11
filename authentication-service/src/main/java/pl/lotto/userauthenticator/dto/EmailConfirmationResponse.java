package pl.lotto.userauthenticator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import pl.lotto.userauthenticator.ConfirmationStatus;

import java.util.List;

public record EmailConfirmationResponse(
        @Schema(description = "Username")
        String username,
        @Schema(description = "Result status")
        boolean success,
        @Schema(description = "List of errors")
        List<String> errors) {

    public EmailConfirmationResponse(String username, ConfirmationStatus status, List<String> errors) {
        this(username, status.equals(ConfirmationStatus.SUCCESS), errors);
    }
}
