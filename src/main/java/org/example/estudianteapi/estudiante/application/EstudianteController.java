package org.example.estudianteapi.estudiante.application;

import org.example.estudianteapi.beca.domain.BecaService;
import org.example.estudianteapi.estudiante.domain.Estudiante;
import org.example.estudianteapi.estudiante.domain.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private BecaService becaService;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.saveEstudiante(estudiante);
    }

    @GetMapping("/{id}")
    public Estudiante getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
    }

    @PutMapping("/{estudianteId}/beca/{becaId}")
    public Estudiante assignBecaToEstudiante(@PathVariable Long estudianteId, @PathVariable Long becaId) {
        Estudiante estudiante = estudianteService.getEstudianteById(estudianteId);
        estudiante.setBeca(becaService.getBecaById(becaId));
        return estudianteService.saveEstudiante(estudiante);
    }
}
