package org.example.estudianteapi.beca.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Beca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double porcentajeCobertura; // Porcentaje que cubre la beca, por ejemplo, 50.0 para 50%

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

