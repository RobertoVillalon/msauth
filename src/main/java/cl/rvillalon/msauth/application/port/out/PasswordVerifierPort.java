package cl.rvillalon.msauth.application.port.out;

public interface PasswordVerifierPort {

    boolean matches(String rawPassword, String passwordHash);
}