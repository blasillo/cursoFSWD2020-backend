package es.ejemplo.backend;

import es.ejemplo.backend.persistencia.entidades.Curso;
import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.persistencia.repositorios.CursosRepositorio;
import es.ejemplo.backend.persistencia.repositorios.RegistroCursosRepositorio;
import es.ejemplo.backend.persistencia.repositorios.UsuariosRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})

@Slf4j
public class RegistroCursosRepositorioTests {

    @Autowired
    private RegistroCursosRepositorio repo;

    @Autowired
    private CursosRepositorio cursosRepo;

    @Autowired
    private UsuariosRepositorio usuariosRepo;


    @Test
    public void deberia_inyectar_repositorio() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void deberia_crear_registro() {

        Usuario estudiante = new Usuario().builder()
                .nombreUsuario("demo")
                .correo("prueba@demo.com")
                .clave("123456")
                .rol(Rol.ESTUDIANTE)
                .build();

        estudiante = usuariosRepo.save( estudiante);
        assertThat( estudiante.getIdUsuario()).isNotNull();

        Usuario profesor = new Usuario().builder()
                .nombreUsuario("profesor")
                .rol(Rol.PROFESOR)
                .correo("profe@demo.com")
                .clave("12344321")
                .build();

        profesor = usuariosRepo.save( profesor );
        assertThat( profesor.getIdUsuario()).isNotNull();

        Curso curso = new Curso( null, "Nombre curso", profesor );

        curso = cursosRepo.save( curso );

        assertThat( curso.getIdCurso()).isNotNull();

        RegistroCurso registroCurso = new RegistroCurso(null, estudiante, curso);

        registroCurso = repo.save( registroCurso);

        log.info( registroCurso.toString());

        assertThat( registroCurso.getId()).isNotNull();

        Optional<RegistroCurso> registroCursoRec = repo.findById( registroCurso.getId() );

        assertThat( registroCursoRec.isPresent() ).isTrue();

        assertThat( registroCursoRec.get().getCurso() ).isEqualTo( curso );
        assertThat( registroCursoRec.get().getEstudiante() ).isEqualTo( estudiante );


    }






}
