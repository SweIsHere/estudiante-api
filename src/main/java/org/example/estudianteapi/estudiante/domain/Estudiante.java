package org.example.estudianteapi.estudiante.domain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.estudianteapi.beca.domain.Beca;

import java.util.List;


@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String carrera;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String fechaNacimiento;

    @Column(nullable = false)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EscalaPago escalaPago;


    // Relación con la beca (N:1)
    @ManyToOne
    @JoinColumn(name = "beca_id", nullable = true)
    private Beca beca;

    public enum EscalaPago {
        scale_A,
        scale_B,
        scale_C,

        scale_D,

        scale_E,


    }

}
