package org.example.estudianteapi.inscripcion.domain;

import org.example.estudianteapi.inscripcion.infrastructure.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion getInscripcionById(Long id) {
        return inscripcionRepository.findById(id).orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }

    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
