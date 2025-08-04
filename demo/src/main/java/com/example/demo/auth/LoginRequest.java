//Clase que pide las credenciales al usuario 

package com.example.demo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para los getters y setters
@Builder //Para construir los objetos
@AllArgsConstructor //genera automáticamente un constructor con todos los atributos
@NoArgsConstructor //Genera un constructor sin parámetros
public class LoginRequest {
    String username;
    String password;

}
