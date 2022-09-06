package es.ejemplo.backend.controladores;


import es.ejemplo.backend.persistencia.entidades.Curso;
import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import es.ejemplo.backend.persistencia.entidades.Usuario;
import es.ejemplo.backend.servicios.CursosServicio;
import es.ejemplo.backend.servicios.RegistroCursosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ProfesoresControlador {

    @Autowired
    private RegistroCursosServicio servicio;

    @Autowired
    private CursosServicio cursosServicio;


    @GetMapping("/api/v1/profesores/{profesorId}/estudiantes")
    public ResponseEntity<?> verEstudiantesPorProfesor ( @PathVariable Long profesorId) {
        Set<Usuario> estudiantes = servicio.obtenerTodosCursosProfesor( profesorId)
                .stream()
                .map( reg -> reg.getEstudiante()  )
                .collect(Collectors.toSet());

        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @PostMapping ("api/v1/profesores/curso")
    public ResponseEntity<?> crearCurso ( @RequestBody Curso curso ) {
        return new ResponseEntity<>( cursosServicio.crearCurso( curso ), HttpStatus.CREATED);
    }

}
