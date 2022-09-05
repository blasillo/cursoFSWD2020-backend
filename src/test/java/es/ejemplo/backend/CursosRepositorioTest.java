package es.ejemplo.backend;

import es.ejemplo.backend.persistencia.entidades.Curso;
import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.persistencia.repositorios.CursosRepositorio;
import es.ejemplo.backend.persistencia.repositorios.UsuariosRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CursosRepositorioTest {

    @Autowired
    private CursosRepositorio repo;

    @Autowired
    private UsuariosRepositorio usuariosRepo;

    @Test
    public void deberia_inyectar_repositorio() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void deberia_hacer_crud_completo() {
        Usuario profesor = new Usuario().builder()
                .nombreUsuario("profesor")
                .rol(Rol.PROFESOR)
                .correo("profe@demo.com")
                .clave("123445")
                .build();

        profesor = usuariosRepo.save( profesor );

        Curso curso = new Curso( null, "Nombre curso", profesor );

        curso = repo.save( curso ); // insertar

        assertThat( curso.getIdCurso()).isNotNull();


        curso.setNombreCurso("Nuevo nombre curso" );

        curso = repo.save (curso); //actualizar

        Curso cursoRecuperado = repo.findById( curso.getIdCurso() ).get(); //recuperar

        assertThat(  cursoRecuperado.getNombreCurso().equals("Nuevo nombre curso") ).isTrue();

        Long cursoId = curso.getIdCurso();

        repo.deleteById( curso.getIdCurso() ); //borrado


        assertThrows(NoSuchElementException.class, () -> {
            repo.findById( cursoId ).get();
        });

    }
}
