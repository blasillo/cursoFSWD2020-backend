package es.ejemplo.backend.persistencia.repositorios;

import es.ejemplo.backend.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepositorio extends JpaRepository<Usuario,Long> {
}
