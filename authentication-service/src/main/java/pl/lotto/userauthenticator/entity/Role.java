package pl.lotto.userauthenticator.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.lotto.userauthenticator.UserRole;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "roles")
@Entity
public class Role {

    @Id
    @SequenceGenerator(name = "rolesIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesIdSeq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;

    public Role() {
    }

    public Role(UserRole role) {
        this.name = role;
    }
}
