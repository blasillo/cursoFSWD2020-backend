package es.ejemplo.backend;


import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.persistencia.repositorios.UsuariosRepositorio;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;


import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})
public class UsuariosRepositorioTests {


    @Autowired
    UsuariosRepositorio repo;

    @Test
    public void deberia_inyectar_ropositorio() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void no_deberia_encontrar_usuarios_si_esta_vacio() {
        List<Usuario> tutorials = repo.findAll();

        assertThat(tutorials).isEmpty();
    }

    @Test
    public void deberia_guardar_un_usuario() {
        Usuario usuario = new Usuario().builder()
                .nombreUsuario("demo")
                .correo("prueba@demo.com")
                .clave("123456")
                .rol(Rol.ESTUDIANTE)
                .build();

        repo.save( usuario);

        List<Usuario> usuarios = repo.findAll();

        assertThat(usuarios).hasSize(1).contains(usuario);

    }

    @Test
    public void deberia_encontrar_usuario_por_id() {
        Usuario usuario1 = new Usuario().builder()
                .nombreUsuario("demo")
                .correo("prueba@demo.com")
                .clave("123456")
                .rol(Rol.ESTUDIANTE)
                .build();

        Usuario usuario2 = new Usuario().builder()
                .nombreUsuario("demo 2")
                .correo("prueba2@demo.com")
                .clave("admin")
                .rol(Rol.ADMINISTRADOR)
                .build();

        usuario1 = repo.save(usuario1);
        usuario2 = repo.save(usuario2);

        Usuario usuarioEncontrado = repo.findById(usuario2.getIdUsuario()).get();

        assertThat(usuarioEncontrado).isEqualTo(usuario2);
    }
    @Test
    public void no_deberia_guardan_cuando_campo_obligatorio_nulo (){

        Usuario usuario1 = new Usuario().builder()
                .nombreUsuario("demo")
                .correo("prueba@demo.com")
                .clave(null)
                .rol(Rol.ESTUDIANTE)
                .build();


        assertThrows(DataIntegrityViolationException.class, () -> {
            repo.save(usuario1);
        });
    }
    @Test
    public void deberia_borrar_usuario_por_id() {

        Usuario usuario1 = new Usuario().builder()
                .nombreUsuario("demo")
                .correo("prueba@demo.com")
                .clave("123456")
                .rol(Rol.ESTUDIANTE)
                .build();

        Usuario usuario2 = new Usuario().builder()
                .nombreUsuario("demo 2")
                .correo("prueba2@demo.com")
                .clave("admin")
                .rol(Rol.ADMINISTRADOR)
                .build();

        Usuario usuario3 = new Usuario().builder()
                .nombreUsuario("demo 3")
                .correo("prueba3@demo.com")
                .clave("password")
                .rol(Rol.PROFESOR)
                .build();


        usuario1 = repo.save(usuario1);
        usuario2 = repo.save(usuario2);
        usuario3 = repo.save(usuario3);

        repo.deleteById(usuario2.getIdUsuario());

        List<Usuario> usuarios = repo.findAll();

        assertThat(usuarios).hasSize(2).contains(usuario1, usuario3);
    }

}
