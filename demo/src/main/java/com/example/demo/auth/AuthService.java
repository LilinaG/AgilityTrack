/*Servicio de autenticación que ofrece la funcionalidad de login y de registro de nuevo usuario.
 */

package com.example.demo.auth;

import org.springframework.stereotype.Service;

import com.example.demo.jwt.JwtService;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service //Indica que esta clase es un servicio de negocio
@RequiredArgsConstructor /*Genera un constructor automáticamente de todos los atributos "final" */

public class AuthService {

    private final UserRepository userRepository; /*Permite guardar usuarios en la BBDD */
    private final JwtService jwtService; /*Es un sesrvicio que genera los tokens JWT para los usuarios */

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    /* Este método recibe el objeto "RegisterRequest" con los datos del nuevo usaurio y
      crea el usuario usando el patron Builder para crear un objeto "user" con los datos
      del formulario del registro. Ademas le asigna un rol por defecto (Role.USER)*/
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER)
            .build();

        /*Guarda el usuario en la BD */
        userRepository.save(user); 

        /*Se genera un token JWT para el usuario registrado mediante
         * el servicio de jwtService. Devuelve el objeto AuhtResponse
         * que contiene el token.
         */
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();

    }

}
