package es.ejemplo.backend;

import es.ejemplo.backend.persistencia.entidades.Curso;
import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.servicios.CursosServicio;
import es.ejemplo.backend.servicios.RegistroCursosServicio;
import es.ejemplo.backend.servicios.UsuariosServicio;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
@Slf4j


public class CursosBackendApplication implements CommandLineRunner {


    private final UsuariosServicio usuariosServicio;
    private final CursosServicio cursosServicio;
    private final RegistroCursosServicio registroCursosServicio;

    public static void main(String[] args) {
        SpringApplication.run(CursosBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info ("Inicializando datos de demos ...");

        creacionUsuariosDemo();
        creacionCursosDemo();
        registrarEstudiantesCursosDemo();
    }

    private void creacionUsuariosDemo () {
        Usuario usuario = new Usuario().builder()
                .nombreUsuario("admin")
                .correo("prueba2@demo.com")
                .clave("admin")
                .rol(Rol.ADMINISTRADOR)
                .build();

        usuariosServicio.guardarUsuario( usuario);

        Usuario profesor = new Usuario().builder()
                .nombreUsuario("profesor")
                .correo("profe@demo.com")
                .clave("lacasadepapel")
                .rol(Rol.PROFESOR)
                .build();

        usuariosServicio.guardarUsuario( profesor);

        Usuario estudiante = new Usuario().builder()
                .nombreUsuario("estudiante1")
                .correo("estudiante@demo.com")
                .clave("123456")
                .rol(Rol.ESTUDIANTE)
                .build();

        usuariosServicio.guardarUsuario( estudiante);
    }

    private void creacionCursosDemo () {

        Usuario profesor = usuariosServicio.encontrarUsuarioPorNombre("profesor");

        Curso curso1 = new Curso(null,"Spring Boot - Introducción", profesor);

        cursosServicio.crearCurso( curso1);

        Curso curso2 = new Curso(null,"Spring Boot - IoC y DI", profesor);
        cursosServicio.crearCurso( curso2);

    }

    private void registrarEstudiantesCursosDemo() {


        Usuario estudiante = new Usuario().builder()
                .nombreUsuario("estudiante2")
                .correo("estudiante2@demo.com")
                .clave("1234567890")
                .rol(Rol.ESTUDIANTE)
                .build();

        usuariosServicio.guardarUsuario( estudiante );

        List<Curso> cursos = cursosServicio.obtenerTodosCursos();

        cursos.forEach( curso -> { registroCursosServicio.guardarRegistro(
                new RegistroCurso(null, estudiante,curso) );} );

    }

}
