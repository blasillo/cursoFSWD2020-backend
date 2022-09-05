package es.ejemplo.backend.persistencia.repositorios;

import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepositorio extends JpaRepository<Usuario,Long> {


    Optional<Usuario> findByNombreUsuario (String nombre);

    List<Usuario> findByRol (Rol rol);
}
