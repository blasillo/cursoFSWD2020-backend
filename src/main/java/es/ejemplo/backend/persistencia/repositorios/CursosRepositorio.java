package es.ejemplo.backend.persistencia.repositorios;

import es.ejemplo.backend.persistencia.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepositorio extends JpaRepository <Curso, Long> {

}
