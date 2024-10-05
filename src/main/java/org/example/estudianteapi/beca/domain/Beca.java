package org.example.estudianteapi.beca.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.estudianteapi.estudiante.domain.Estudiante;

import java.util.List;


@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Beca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String fechaInicio;

    @Column(nullable = false)
    private String fechaFin;

    @Column(nullable = false)
    private String nombre;

    // Relación con estudiantes (1:N)
    @OneToMany(mappedBy = "beca", cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;
}

