package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroCursosServicioImpl implements RegistroCursosServicio {
    @Override
    public RegistroCurso guardarRegistro(RegistroCurso registro) {
        return null;
    }

    @Override
    public List<RegistroCurso> obtenerTodosCursosEstudiante(Long idEstudiante) {
        return null;
    }

    @Override
    public List<RegistroCurso> obtenerTodosCursosProfesor(Long idProfesor) {
        return null;
    }

    @Override
    public List<RegistroCurso> obtenerTodosRegistroCursos() {
        return null;
    }
}
