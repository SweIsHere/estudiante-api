package org.example.estudianteapi.beca.infrastructure;


import org.example.estudianteapi.beca.domain.Beca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BecaRepository extends JpaRepository<Beca, Long> {

}
