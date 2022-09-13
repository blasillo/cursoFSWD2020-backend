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


    public static void main(String[] args) {
        SpringApplication.run(CursosBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info ("Iniciando aplicación ...");


    }


}
