/*
Filtro de autenticación JWT personalizado en Spring Security. Su función es interceptar cada petición HTTP, 
extraer el token JWT si existe, validarlo y, si todo está bien, autenticar al usuario automáticamente para 
el resto del sistema.
*/


package com.example.demo.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
//OncePerRequestFilter clase abstracta base de Spring Security usada para crear filtros que se ejecutan una vez por cada petición HTTP
@Override
protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
        throws ServletException, IOException{

/*
doFilterInternal, método que sobrescribo que tengo que implimentar obligatoriamente de OncePerRequestFilter
Ejecuta los filtros
*/
    
// Llamada la método getTokenFromRequest que extrae el token JWT
    final String token = getTokenFromRequest(request);

    //Si no hay token, continúa sin autenticar
    if (token==null){
        filterChain.doFilter(request, response);
        return;
    }
    //Continuar la cadena de filtros
    filterChain.doFilter(request, response);
    }
    /* Método que nos devuelve el token String
        Busca en la petición HTTP el header, comprueba si tiene texto, 
        si empieza por Bearer (formato típico de los tokens JWT) y 
        si el formato es correcto, extra y devuelve solo el token quitando 
        el Bearer, si no hay token o está mal formado devuelve un null
    */
    private String getTokenFromRequest(HttpServletRequest request){
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

        //StringUtils --> librería de Java
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }
        return null;
    }
    
}