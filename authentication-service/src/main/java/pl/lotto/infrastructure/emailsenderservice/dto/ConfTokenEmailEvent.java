package pl.lotto.infrastructure.emailsenderservice.dto;

import java.io.Serializable;
import java.time.Instant;

public record ConfTokenEmailEvent(String email, String token, Instant expiryAt) implements Serializable {
}
