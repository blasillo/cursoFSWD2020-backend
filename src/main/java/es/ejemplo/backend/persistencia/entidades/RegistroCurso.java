package es.ejemplo.backend.persistencia.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CURSOS_ESTUDIANTES")
public class RegistroCurso {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name ="id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_estudiante")
    private Usuario estudiante;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name ="id_curso")
    private Curso curso;

}
