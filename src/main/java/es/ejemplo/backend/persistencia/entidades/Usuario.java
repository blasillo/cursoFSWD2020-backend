package es.ejemplo.backend.persistencia.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_usuario", nullable = false,updatable = false)
    private Long idUsuario;

    @Column (name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column (name="correo", nullable = false, length = 100)
    private String correo;

    @Column (name="clave", nullable = false, length = 255)
    private String clave;

    @Column (name="rol", nullable = false)
    private Rol rol;

    //@Transient
    private String token;
}
