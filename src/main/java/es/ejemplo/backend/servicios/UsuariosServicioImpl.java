package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.persistencia.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuariosServicioImpl implements UsuariosServicio {

    @Autowired
    private UsuariosRepositorio repositorio;

    @Autowired
    private PasswordEncoder cifradorClave;


    @Override
    public Usuario guardarUsuario(Usuario usuario) { // nuevo usuario
        usuario.setClave(  cifradorClave.encode(usuario.getClave()) );
        return repositorio.save( usuario );
    }

    @Override
    public Usuario encontrarUsuarioPorNombre(String nombre) {
        return repositorio.findByNombreUsuario( nombre).orElse(null);
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }
}
