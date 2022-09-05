package es.ejemplo.backend.persistencia.repositorios;

import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroCursosRepositorio extends JpaRepository<RegistroCurso,Long > {


    List<RegistroCurso> findByCursoProfesorIdUsuario (Long id );

    List<RegistroCurso> findByEstudianteIdUsuario (Long id);


}
