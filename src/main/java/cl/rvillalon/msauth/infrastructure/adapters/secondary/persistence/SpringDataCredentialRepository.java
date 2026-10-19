package cl.rvillalon.msauth.infrastructure.adapters.secondary.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCredentialRepository
        extends JpaRepository<CredentialJpaEntity, Long> {

    Optional<CredentialJpaEntity> findByRunIgnoreCaseOrEmailIgnoreCase(
            String run,
            String email
    );
}