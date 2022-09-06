package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.Curso;
import es.ejemplo.backend.persistencia.repositorios.CursosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CursosServicioImpl implements CursosServicio {

    @Autowired
    private CursosRepositorio repositorio;

    @Override
    public Curso crearCurso(Curso curso) {
        return repositorio.save( curso );
    }

    @Override
    public Curso actualizarCurso(Curso curso) {
        return repositorio.save( curso );
    }

    @Override
    public void borrarCurso(Long idCurso) {
        repositorio.deleteById( idCurso );
    }

    @Override
    public List<Curso> obtenerTodosCursos() {
        return repositorio.findAll();
    }
}
