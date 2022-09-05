package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Usuario;

import java.util.List;

public interface UsuariosServicio {
    Usuario guardarUsuario (Usuario usuario);

    Usuario encontrarUsuarioPorNombre (String nombre);

    List<Usuario> obtenerTodosUsuarios ();

    Usuario actualizarUsuario (Usuario usuario);
}
