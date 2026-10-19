package cl.rvillalon.msauth.infrastructure.adapters.secondary.persistence;

import cl.rvillalon.msauth.application.port.out.CredentialRepositoryPort;
import cl.rvillalon.msauth.domain.model.UserCredential;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CredentialRepositoryAdapter
        implements CredentialRepositoryPort {

    private final SpringDataCredentialRepository repository;

    public CredentialRepositoryAdapter(
            SpringDataCredentialRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Optional<UserCredential> findByIdentifier(String identifier) {
        return repository
                .findByRunIgnoreCaseOrEmailIgnoreCase(
                        identifier,
                        identifier
                )
                .map(entity -> new UserCredential(
                        entity.getId(),
                        entity.getUserId(),
                        entity.getRun(),
                        entity.getEmail(),
                        entity.getPasswordHash(),
                        entity.isEnabled()
                ));
    }
}