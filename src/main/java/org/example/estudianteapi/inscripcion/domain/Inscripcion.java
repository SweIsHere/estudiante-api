package org.example.estudianteapi.inscripcion.domain;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.estudianteapi.estudiante.domain.Estudiante;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ciclo;

    @Column(nullable = false)
    private Integer nroVez;

    // Relación con estudiante (N:1)
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;
}

