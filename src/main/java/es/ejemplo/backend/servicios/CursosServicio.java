package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Curso;

import java.util.List;

public interface CursosServicio {

    Curso crearCurso (Curso curso);

    Curso actualizarCurso(Curso curso);

    void borrarCurso (Long idCurso);

    List<Curso> obtenerTodosCursos();
}
