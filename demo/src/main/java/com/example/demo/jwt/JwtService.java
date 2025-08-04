/*
 * Clase se encarga de generar tokens JWT (JSON Web Token), 
 * que son usados para autenticar usuarios
 */

package com.example.demo.jwt;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924"; //la Key que tu quieras

    /*
     * Método para generar un token JWT para un usario
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);

    }
    /*Método que hace todo el trabajo real de creación de un token */
    private String getToken(Map<String,Object> extraClaims, UserDetails user){
        return Jwts
            .builder()
            .setClaims(extraClaims) /*Añade cualquier información adicional que quieras guardar en el token (ej. rol, email...) */
            .setSubject(user.getUsername()) /* Define el usuario principal (el "dueño" del token). Por lo general, el nombre de usuario.*/
            .setIssuedAt(new Date(System.currentTimeMillis())) /*Fecha de creación del token */
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24)) /*Fecha de caducidad. En este caso, el token caduca en 24 minutos (1000 ms * 60 seg * 24 min) */
            .signWith(getKey(), SignatureAlgorithm.HS256) /*Firma el token con la clave secreta usando el algoritmo de firma HS256 */
            .compact(); /*Genera el token como una cadena JWT codificada lista para enviar al cliente. */
            
    }

    /*
     * Decodifica la clave secreta en formato Base64.
     * Genera una clave Key que servirá para firmar el token usando 
     * el algoritmo HMAC SHA-256 (HS256).
     */
    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
