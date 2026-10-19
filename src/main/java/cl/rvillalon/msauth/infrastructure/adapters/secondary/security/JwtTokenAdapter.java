package cl.rvillalon.msauth.infrastructure.adapters.secondary.security;

import cl.rvillalon.msauth.application.port.out.TokenIssuerPort;
import cl.rvillalon.msauth.domain.model.AuthenticatedUser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenAdapter implements TokenIssuerPort {

    private final SecretKey signingKey;
    private final long expirationSeconds;

    public JwtTokenAdapter(
            SecretKey jwtSigningKey,
            @Value("${security.jwt.expiration-seconds:900}")
            long expirationSeconds
    ) {
        if (expirationSeconds <= 0) {
            throw new IllegalArgumentException(
                    "JWT expiration must be positive"
            );
        }

        this.signingKey = jwtSigningKey;
        this.expirationSeconds = expirationSeconds;
    }

    @Override
    public String issueToken(AuthenticatedUser user) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plusSeconds(expirationSeconds);

        return Jwts.builder()
                .subject(user.userId().toString())
                .claim("run", user.run())
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiresAt))
                .signWith(signingKey)
                .compact();
    }

    @Override
    public long expiresInSeconds() {
        return expirationSeconds;
    }
}