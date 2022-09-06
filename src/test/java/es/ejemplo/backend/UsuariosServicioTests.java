package es.ejemplo.backend;

import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.servicios.UsuariosServicio;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class UsuariosServicioTests {


    @Autowired
    private UsuariosServicio servicio;

    @Autowired
    private PasswordEncoder cifradorClave;


    @Test
    public void deberia_inyectar_servicio() {
        assertThat(servicio).isNotNull();
    }


    @Test
    public void deberia_crear_usuario() {

        Usuario usuario = new Usuario().builder()
                .nombreUsuario("demo")
                .correo("prueba@demo.com")
                .clave("123456")
                .rol(Rol.ESTUDIANTE)
                .build();


        Usuario usuarioguardado = servicio.guardarUsuario( usuario );

        assertThat( usuarioguardado ).isNotNull();

        assertThat( usuarioguardado.getIdUsuario()).isNotNull();

        Optional <Usuario> usuariorecuperado = Optional.ofNullable(servicio.encontrarUsuarioPorNombre(usuario.getNombreUsuario()));

        assertThat( usuariorecuperado.isPresent() ).isTrue();



        log.info( usuariorecuperado.get().toString());

    }

    @Test
    public void deberia_guardar_usuario_clave_noclaro() {

        Usuario usuario = new Usuario().builder()
                .nombreUsuario("demo 2")
                .correo("prueba2@demo.com")
                .clave("admin")
                .rol(Rol.ADMINISTRADOR)
                .build();

        Usuario usuarioguardado = servicio.guardarUsuario( usuario );
        assertThat( usuarioguardado ).isNotNull();
        assertThat( usuarioguardado.getIdUsuario()).isNotNull();

        String clave =  cifradorClave.encode( "admin" );


        assertThat( cifradorClave.matches("admin" , usuarioguardado.getClave()) ).isTrue();

        log.info ( usuarioguardado.toString());

    }
}
