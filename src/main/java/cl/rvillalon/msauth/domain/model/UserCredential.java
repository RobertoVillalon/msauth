package cl.rvillalon.msauth.domain.model;

public record UserCredential(
        Long id,
        Long userId,
        String run,
        String email,
        String passwordHash,
        boolean enabled
) {
}