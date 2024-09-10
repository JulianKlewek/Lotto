package pl.lotto.userauthenticator.dto;

import pl.lotto.userauthenticator.ConfirmationStatus;

import java.util.List;

public record ConfirmationResult(ConfirmationStatus status, List<String> errors) {
}
