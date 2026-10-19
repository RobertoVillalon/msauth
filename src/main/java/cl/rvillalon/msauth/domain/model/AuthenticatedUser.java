package cl.rvillalon.msauth.domain.model;

public record AuthenticatedUser(
        Long userId,
        String run
) {
}