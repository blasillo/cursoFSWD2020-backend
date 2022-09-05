package es.ejemplo.backend.persistencia.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="CURSOS")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_curso", nullable = false, updatable = false)
    private Long idCurso;

    @Column (name ="nombre_curso", length = 300, nullable = false)
    private String nombreCurso;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name="id_profesor")
    private Usuario profesor;

    //private List<Usuario> alumnosRegistrados;
}
