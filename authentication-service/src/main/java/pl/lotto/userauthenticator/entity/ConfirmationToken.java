package pl.lotto.userauthenticator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "confirmation_token")
@Entity
public class ConfirmationToken {

    private static final int EXPIRATION_HOURS = 24;

    @Id
    private String token;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "expiry_at")
    private Instant expiryAt;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ConfirmationToken(String token, Instant createdAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.user = user;
        this.expiryAt = calculateExpiryAt(createdAt);
    }

    private Instant calculateExpiryAt(Instant createdAt) {
        return createdAt.plus(EXPIRATION_HOURS, ChronoUnit.HOURS);
    }
}
