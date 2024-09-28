package org.example.estudianteapi.inscripcion.infrastructure;


import org.example.estudianteapi.inscripcion.domain.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

}
