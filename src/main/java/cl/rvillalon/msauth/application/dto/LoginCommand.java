package cl.rvillalon.msauth.application.dto;

public record LoginCommand(
        String identifier,
        String password
) {}