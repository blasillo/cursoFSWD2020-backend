package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Curso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosServicioImpl implements CursosServicio {
    @Override
    public Curso crearCurso(Curso curso) {
        return null;
    }

    @Override
    public Curso actualizarCurso(Curso curso) {
        return null;
    }

    @Override
    public void borrarCurso(Long idCurso) {

    }

    @Override
    public List<Curso> obtenerTodosCursos() {
        return null;
    }
}
