package org.example.estudianteapi.inscripcion.application;

import org.example.estudianteapi.inscripcion.domain.Inscripcion;
import org.example.estudianteapi.inscripcion.domain.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @PostMapping
    public Inscripcion createInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.saveInscripcion(inscripcion);
    }

    @GetMapping("/{id}")
    public Inscripcion getInscripcionById(@PathVariable Long id) {
        return inscripcionService.getInscripcionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
    }
}
