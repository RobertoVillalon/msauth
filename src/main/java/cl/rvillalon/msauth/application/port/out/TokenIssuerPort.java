package cl.rvillalon.msauth.application.port.out;


import cl.rvillalon.msauth.domain.model.AuthenticatedUser;

public interface TokenIssuerPort {

    String issueToken(AuthenticatedUser user);

    long expiresInSeconds();
}