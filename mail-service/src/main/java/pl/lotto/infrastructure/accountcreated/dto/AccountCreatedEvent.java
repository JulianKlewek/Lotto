package pl.lotto.infrastructure.accountcreated.dto;

import java.io.Serializable;
import java.time.Instant;

public record AccountCreatedEvent(String email, String token, Instant expiryAt) implements Serializable {

}
