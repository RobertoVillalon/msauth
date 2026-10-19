package cl.rvillalon.msauth.application.port.in;

import cl.rvillalon.msauth.application.dto.LoginCommand;
import cl.rvillalon.msauth.application.dto.LoginResult;

public interface LoginUseCase {
    LoginResult login(LoginCommand command);
}