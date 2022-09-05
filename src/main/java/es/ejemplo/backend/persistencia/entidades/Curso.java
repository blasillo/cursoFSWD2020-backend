package es.ejemplo.backend.persistencia.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

//
public class Curso {

    private Long idCurso;

    private String nombreCurso;

    private Usuario profesor;

    //private List<Usuario> alumnosRegistrados;
}
