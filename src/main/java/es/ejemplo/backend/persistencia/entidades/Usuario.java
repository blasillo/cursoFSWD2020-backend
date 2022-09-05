package es.ejemplo.backend.persistencia.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//
public class Usuario {

    private Long idUsuario;

    private String nombreUsuario;

    private String correo;

    private String clave;

    private Rol rol;

    //@Transient
    private String token;
}
