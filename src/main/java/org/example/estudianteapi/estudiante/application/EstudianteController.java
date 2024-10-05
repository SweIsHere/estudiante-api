package org.example.estudianteapi.estudiante.application;

import org.example.estudianteapi.beca.domain.BecaService;
import org.example.estudianteapi.estudiante.domain.Estudiante;
import org.example.estudianteapi.estudiante.domain.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/by-email")
    public ResponseEntity<Estudiante> obtenerEstudiantePorCorreo(@RequestParam String correo) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorCorreo(correo);
        return estudiante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
