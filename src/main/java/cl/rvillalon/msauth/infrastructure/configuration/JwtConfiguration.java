package cl.rvillalon.msauth.infrastructure.configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfiguration {

    @Bean
    public SecretKey jwtSigningKey(
            @Value("${security.jwt.secret-base64}") String secret
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);

        // HS256 requires at least 256 bits of key material.
        if (keyBytes.length < 32) {
            throw new IllegalStateException(
                    "JWT secret must contain at least 32 decoded bytes");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }
}