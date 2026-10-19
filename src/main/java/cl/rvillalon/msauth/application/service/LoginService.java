package cl.rvillalon.msauth.application.service;

import cl.rvillalon.msauth.application.dto.LoginCommand;
import cl.rvillalon.msauth.application.dto.LoginResult;
import cl.rvillalon.msauth.application.port.in.LoginUseCase;
import cl.rvillalon.msauth.application.port.out.CredentialRepositoryPort;
import cl.rvillalon.msauth.application.port.out.PasswordVerifierPort;
import cl.rvillalon.msauth.application.port.out.TokenIssuerPort;
import cl.rvillalon.msauth.domain.exception.AccountDisabledException;
import cl.rvillalon.msauth.domain.exception.InvalidCredentialsException;
import cl.rvillalon.msauth.domain.model.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final CredentialRepositoryPort credentialRepository;
    private final PasswordVerifierPort passwordVerifier;
    private final TokenIssuerPort tokenIssuer;

    public LoginService(
            CredentialRepositoryPort credentialRepository,
            PasswordVerifierPort passwordVerifier,
            TokenIssuerPort tokenIssuer
    ) {
        this.credentialRepository = credentialRepository;
        this.passwordVerifier = passwordVerifier;
        this.tokenIssuer = tokenIssuer;
    }

    @Override
    public LoginResult login(LoginCommand command) {
        var credential = credentialRepository
                .findByIdentifier(command.identifier())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordVerifier.matches(
                command.password(),
                credential.passwordHash())) {
            throw new InvalidCredentialsException();
        }

        if (!credential.enabled()) {
            throw new AccountDisabledException();
        }

        var authenticatedUser = new AuthenticatedUser(
                credential.userId(),
                credential.run()
        );

        String token = tokenIssuer.issueToken(authenticatedUser);

        return new LoginResult(
                token,
                "Bearer",
                tokenIssuer.expiresInSeconds()
        );
    }
}