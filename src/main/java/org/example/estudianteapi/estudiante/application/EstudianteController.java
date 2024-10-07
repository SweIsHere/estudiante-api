package org.example.estudianteapi.estudiante.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.estudianteapi.beca.domain.BecaService;
import org.example.estudianteapi.estudiante.domain.Estudiante;
import org.example.estudianteapi.estudiante.domain.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private BecaService becaService;

    @Operation(summary = "Obtener todos los estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes devuelta exitosamente")
    })
    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    @Operation(summary = "Crear un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante creado exitosamente")
    })
    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.saveEstudiante(estudiante);
    }

    @Operation(summary = "Obtener un estudiante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/{id}")
    public Estudiante getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    @Operation(summary = "Obtener un estudiante por correo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado por correo"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/by-email")
    public ResponseEntity<Estudiante> obtenerEstudiantePorCorreo(@RequestParam String correo) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorCorreo(correo);
        return estudiante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener la escala de pago por correo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escala de pago devuelta exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado por correo")
    })
    @GetMapping("/escala-pago/{email}")
    public Estudiante.EscalaPago getEscalaPagoByCorreo(@PathVariable String email) {
        return estudianteService.getEscalaPagoByCorreo(email);
    }

    @Operation(summary = "Eliminar un estudiante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        if (estudianteService.existsById(id)) {
            estudianteService.deleteEstudiante(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Asignar una beca a un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beca asignada exitosamente al estudiante"),
            @ApiResponse(responseCode = "404", description = "Estudiante o beca no encontrados")
    })
    @PutMapping("/{estudianteId}/beca/{becaId}")
    public ResponseEntity<Estudiante> assignBecaToEstudiante(@PathVariable Long estudianteId, @PathVariable Long becaId) {
        try {
            Estudiante estudianteActualizado = estudianteService.assignBecaToEstudiante(estudianteId, becaId);
            return ResponseEntity.ok(estudianteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Echo confirmer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beca asignada exitosamente al estudiante")
    })
    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("Status", "UP");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
