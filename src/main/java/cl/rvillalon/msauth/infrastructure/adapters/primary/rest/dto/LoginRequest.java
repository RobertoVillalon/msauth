package cl.rvillalon.msauth.infrastructure.adapters.primary.rest.dto;


import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String identifier,
        @NotBlank String password
) {}