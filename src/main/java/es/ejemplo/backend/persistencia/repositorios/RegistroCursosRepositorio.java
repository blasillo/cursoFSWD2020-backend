package es.ejemplo.backend.persistencia.repositorios;

import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroCursosRepositorio extends JpaRepository<RegistroCurso,Long > {
}
