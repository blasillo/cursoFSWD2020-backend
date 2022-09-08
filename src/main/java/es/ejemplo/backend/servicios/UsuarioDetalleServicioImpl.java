package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.persistencia.repositorios.UsuariosRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UsuarioDetalleServicioImpl implements UserDetailsService {

    @Autowired
    private UsuariosRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        log.info( "UsuarioDetalleServicioImpl");
        Usuario usuario = repositorio.findByNombreUsuario( nombre)
                .orElse( null);

        if (usuario == null) { throw new UsernameNotFoundException(nombre);}

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority( usuario.getRol().name() ));

        return new User(usuario.getNombreUsuario(), usuario.getClave(), authorities);
    }


}
