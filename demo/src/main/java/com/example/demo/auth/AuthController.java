
//Controlador REST con endpoints públicos para login y registro.
package com.example.demo.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController //Marca esta clase como un controlador REST (devuelve datos no vistas HTML)
@RequestMapping("/auth") //Define una ruta base común para todos los endpoints de esta clase
@RequiredArgsConstructor //Genera automáticamente un constructor con los argumentos necesarios

public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "login") //Define un endpoint POST en /auth/login
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register") //Define un endpoint POST en /auth/register
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

}
