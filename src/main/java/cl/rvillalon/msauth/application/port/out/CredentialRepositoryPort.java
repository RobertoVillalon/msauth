package cl.rvillalon.msauth.application.port.out;

import cl.rvillalon.msauth.domain.model.UserCredential;
import java.util.Optional;

public interface CredentialRepositoryPort {

    Optional<UserCredential> findByIdentifier(String identifier);
}