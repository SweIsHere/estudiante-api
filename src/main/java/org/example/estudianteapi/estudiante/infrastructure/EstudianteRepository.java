package org.example.estudianteapi.estudiante.infrastructure;


import org.example.estudianteapi.estudiante.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByCarrera(String carrera);
    Optional<Estudiante> findByCorreo(String correo);

}
