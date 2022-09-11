package es.ejemplo.backend.controladores;

import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.seguridad.JwtProveedor;
import es.ejemplo.backend.servicios.CursosServicio;
import es.ejemplo.backend.servicios.UsuariosServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
public class UsuariosControlador {

    @Autowired
    private UsuariosServicio usuariosServicio;

    @Autowired
    private CursosServicio cursosServicio;

    @Autowired
    private JwtProveedor jwtProveedor;

    @GetMapping("/api/v1/usuarios/login")
    public ResponseEntity<?> getUser(Principal principal){

        log.info ("Solicitud login");

        if(principal == null){
            return ResponseEntity.ok(principal);
        }
        log.info ("Solicitud login : {}", principal.toString());
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        Usuario usuario = usuariosServicio.encontrarUsuarioPorNombre(authenticationToken.getName());

        usuario.setToken(jwtProveedor.generarToken(authenticationToken));
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/api/v1/usuarios/registro")
    public ResponseEntity<?> registroUsuarios (@RequestBody Usuario usuario){

        if ( usuariosServicio.encontrarUsuarioPorNombre( usuario.getNombreUsuario()) != null)  {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else {
            usuario.setRol(Rol.ESTUDIANTE);
            return new ResponseEntity<>( usuariosServicio.guardarUsuario( usuario ), HttpStatus.CREATED);
        }
    }

    @GetMapping("/api/v1/usuarios/cursos")
    public ResponseEntity<?> verTodosCursos(){
        return ResponseEntity.ok(cursosServicio.obtenerTodosCursos() );
    }



}
