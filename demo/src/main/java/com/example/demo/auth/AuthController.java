
//Controlador REST con endpoints públicos para login y registro.
package com.example.demo.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController //Marca esta clase como un controlador REST (devuelve datos no vistas HTML)
@RequestMapping("/auth") //Define una ruta base común para todos los endpoints de esta clase
@RequiredArgsConstructor //Genera automáticamente un constructor con los argumentos necesarios

public class AuthController {
    @PostMapping(value = "login") //Define un endpoint POST en /auth/login
    public String login()
    {
        return "Login from public endpoint";
    }

    @PostMapping(value = "register") //Define un endpoint POST en /auth/register
    public String register()
    {
        return "Register from public endpoint";
    }

}
