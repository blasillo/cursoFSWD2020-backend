package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.RegistroCurso;

import java.util.List;

public interface RegistroCursosServicio {

    RegistroCurso guardarRegistro (RegistroCurso registro);

    List<RegistroCurso> obtenerTodosCursosEstudiante (Long idEstudiante);

    List<RegistroCurso> obtenerTodosCursosProfesor (Long idProfesor);

    List<RegistroCurso> obtenerTodosRegistroCursos();
}
