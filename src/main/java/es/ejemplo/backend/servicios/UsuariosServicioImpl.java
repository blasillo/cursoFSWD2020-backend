package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServicioImpl implements UsuariosServicio {
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario encontrarUsuarioPorNombre(String nombre) {
        return null;
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return null;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return null;
    }
}
