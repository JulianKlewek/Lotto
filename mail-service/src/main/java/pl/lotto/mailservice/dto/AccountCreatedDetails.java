package pl.lotto.mailservice.dto;

import pl.lotto.mailservice.AccountCreatedProperties;

import java.time.Instant;

public record AccountCreatedDetails(String email, String token, Instant expiryAt) implements AccountCreatedProperties {

    @Override
    public String getConfirmationToken() {
        return this.token;
    }

    @Override
    public Instant getExpirationTime() {
        return this.expiryAt;
    }

    @Override
    public String getRecipient() {
        return this.email;
    }

}
