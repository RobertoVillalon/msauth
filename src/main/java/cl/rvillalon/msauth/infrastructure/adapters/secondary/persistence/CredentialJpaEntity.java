package cl.rvillalon.msauth.infrastructure.adapters.secondary.persistence;

import jakarta.persistence.*;

@Entity
@Table(
        name = "credentials",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_credentials_run",
                        columnNames = "run"
                ),
                @UniqueConstraint(
                        name = "uk_credentials_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_credentials_user_id",
                        columnNames = "user_id"
                )
        }
)
public class CredentialJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 10)
    private String run;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false)
    private boolean enabled = true;

    protected CredentialJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRun() {
        return run;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isEnabled() {
        return enabled;
    }
}