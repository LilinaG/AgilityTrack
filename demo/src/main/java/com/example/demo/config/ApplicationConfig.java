/*
 * Configura la seguridad de la aplicacion, específicamente la autenticación. 
 * Aquí se definen los beans esenciales para que Spring Security funcione correctamente 
 * con los usuarios personalizados guardados en base de datos.
 */


package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration /*definir beans */
@RequiredArgsConstructor /*genera un constructor con los atributos final (en este caso, userRepository) */
public class ApplicationConfig {

    /*repositorio que se usará para cargar los usuarios desde la base de datos (es decir, encontrar por nombre de usuario) */
    private final UserRepository userRepository;

    /*valida si el usuario y la contraseña son correctos cuando alguien intenta iniciar sesión */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
    /* Configura el proveedor de autenticación
     * Carga el usuario desde la base de datos.
     * Verifica la contraseña encriptada (con BCrypt).
     * Si todo es correcto, autentica al usuario.
     */
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider();
    }
    /*
     * Define cómo se van a codificar las contraseñas.
     * Se usa tanto para guardar como para verificar contraseñas.
     */
    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    /*Devuelve una función (lambda) que:
        Busca un usuario por su nombre de usuario en la base de datos.
        Si lo encuentra, lo devuelve.
        Si no, lanza una excepción.
     */
    private UserDetailsService userDetailService() {
       return username -> userRepository.findByUsername(username)
       .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
