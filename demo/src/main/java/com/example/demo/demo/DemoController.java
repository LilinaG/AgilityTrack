//Controlador REST protegido que sólo es accesible si el usuario está autenticado.
//Ruta completa del endpoint: POST /api/v1/demo
//Como este endpoint no está en /auth/**, Spring Security requiere autenticación.
//Por ahora, como no hay usuarios definidos en memoria ni token JWT, se usará el formulario de login por defecto generado por .formLogin(withDefaults()).
//package com.example.demo.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping("value = demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }

}
