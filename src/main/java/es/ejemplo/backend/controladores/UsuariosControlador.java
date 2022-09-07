package es.ejemplo.backend.controladores;

import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.servicios.CursosServicio;
import es.ejemplo.backend.servicios.UsuariosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UsuariosControlador {

    @Autowired
    private UsuariosServicio usuariosServicio;

    @Autowired
    private CursosServicio cursosServicio;



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
