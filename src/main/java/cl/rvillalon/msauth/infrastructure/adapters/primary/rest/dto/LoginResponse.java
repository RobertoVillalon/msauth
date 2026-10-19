package cl.rvillalon.msauth.infrastructure.adapters.primary.rest.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {}