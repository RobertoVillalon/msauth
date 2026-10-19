package cl.rvillalon.msauth.application.dto;

public record LoginResult(
        String accessToken,
        String tokenType,
        long expiresIn
) {}