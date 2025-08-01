
//Define la configuración de seguridad de la app, usando Spring Security.
package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration //Indica que esta clase proporciona una configuración a Spring.
@EnableWebSecurity //Activa la seguridad web 
@RequiredArgsConstructor // 
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Desactiva la protección CSRF (para facilitar pruebas con Postman)
            .authorizeHttpRequests(auth -> auth //configura quién puede acceder a qué
                .requestMatchers("/auth/**").permitAll() // es público (permitAll). Deja pasar sin autenticación cualquier URL que empiece por /auth/
                .anyRequest().authenticated() // El resto necesita autenticación. El resto de URLs requiere autenticación
            )
            .formLogin(withDefaults()) // Habilita formulario de login por defecto de Spring Security
            .build();
    }

}
