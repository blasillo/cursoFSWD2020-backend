package es.ejemplo.backend.persistencia.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//
public class RegistroCurso {

    private Long id;

    private Usuario estudiante;

    private Curso curso;
}
