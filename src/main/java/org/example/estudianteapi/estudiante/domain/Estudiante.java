package org.example.estudianteapi.estudiante.domain;
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


    // Relación con la beca (N:1)
    @ManyToOne
    @JoinColumn(name = "beca_id")
    private Beca beca;


}
