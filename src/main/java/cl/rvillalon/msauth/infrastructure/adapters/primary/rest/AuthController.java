package cl.rvillalon.msauth.infrastructure.adapters.primary.rest;

import cl.rvillalon.msauth.application.dto.LoginCommand;
import cl.rvillalon.msauth.application.dto.LoginResult;
import cl.rvillalon.msauth.application.port.in.LoginUseCase;
import cl.rvillalon.msauth.infrastructure.adapters.primary.rest.dto.LoginRequest;
import cl.rvillalon.msauth.infrastructure.adapters.primary.rest.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResult result = loginUseCase.login(
                new LoginCommand(
                        request.identifier(),
                        request.password()
                )
        );

        return ResponseEntity.ok(
                new LoginResponse(
                        result.accessToken(),
                        result.tokenType(),
                        result.expiresIn()
                )
        );
    }
}