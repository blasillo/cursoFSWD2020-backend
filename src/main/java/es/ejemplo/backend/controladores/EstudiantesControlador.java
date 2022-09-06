package es.ejemplo.backend.controladores;

import es.ejemplo.backend.persistencia.entidades.Curso;
import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import es.ejemplo.backend.servicios.RegistroCursosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EstudiantesControlador {

    @Autowired
    private RegistroCursosServicio registroCursosServicio;


    @GetMapping("/api/v1/estudiantes/{estudianteId}/cursos")
    public ResponseEntity<?> verTodosCursosPorEstudiante (@PathVariable Long estudianteId) {
        List<Curso> cursos =
                registroCursosServicio.obtenerTodosCursosEstudiante(estudianteId)
                        .stream()
                        .map( reg -> reg.getCurso() )
                        .collect(Collectors.toList());
        return new ResponseEntity<>( cursos , HttpStatus.OK);
    }

    @PostMapping("/api/v1/estudiantes/inscripcion")
    public ResponseEntity<?> inscripcionEnCurso (@RequestBody RegistroCurso reg){
        return new ResponseEntity<>(registroCursosServicio.guardarRegistro(reg), HttpStatus.CREATED);
    }


}
