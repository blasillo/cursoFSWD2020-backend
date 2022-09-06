package es.ejemplo.backend.servicios;

import es.ejemplo.backend.persistencia.entidades.RegistroCurso;
import es.ejemplo.backend.persistencia.repositorios.RegistroCursosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RegistroCursosServicioImpl implements RegistroCursosServicio {

    @Autowired
    private RegistroCursosRepositorio repositorio;


    @Override
    public RegistroCurso guardarRegistro(RegistroCurso registro) {
        return repositorio.save(registro);
    }

    @Override
    public List<RegistroCurso> obtenerTodosCursosEstudiante(Long idEstudiante) {
        return repositorio.findByEstudianteIdUsuario( idEstudiante);
    }

    @Override
    public List<RegistroCurso> obtenerTodosCursosProfesor(Long idProfesor) {
        return repositorio.findByCursoProfesorIdUsuario( idProfesor);
    }

    @Override
    public List<RegistroCurso> obtenerTodosRegistroCursos() {
        return repositorio.findAll();
    }
}
