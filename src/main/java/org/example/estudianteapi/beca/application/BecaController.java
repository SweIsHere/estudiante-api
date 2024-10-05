package org.example.estudianteapi.beca.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.estudianteapi.beca.domain.Beca;
import org.example.estudianteapi.beca.domain.BecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/becas")
public class BecaController {

    @Autowired
    private BecaService becaService;

    @Operation(summary = "Obtener todas las becas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de becas devuelta exitosamente")
    })
    @GetMapping
    public List<Beca> getAllBecas() {
        return becaService.getAllBecas();
    }

    @Operation(summary = "Crear una nueva beca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beca creada exitosamente")
    })
    @PostMapping
    public Beca createBeca(@RequestBody Beca beca) {
        return becaService.saveBeca(beca);
    }

    @Operation(summary = "Obtener una beca por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beca encontrada"),
            @ApiResponse(responseCode = "404", description = "Beca no encontrada")
    })
    @GetMapping("/{id}")
    public Beca getBecaById(@PathVariable Long id) {
        return becaService.getBecaById(id);
    }

    @Operation(summary = "Eliminar una beca por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beca eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Beca no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeca(@PathVariable Long id) {
        try {
            becaService.deleteBeca(id);
            return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found si la beca no existe
        }
    }

    @Operation(summary = "Actualizar una beca por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beca actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Beca no encontrada")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Beca> updateBeca(@PathVariable Long id, @RequestBody Beca becaDetalles) {
        try {
            Beca beca = becaService.getBecaById(id);

            beca.setNombre(becaDetalles.getNombre());
            beca.setPorcentajeCobertura(becaDetalles.getPorcentajeCobertura());
            beca.setFechaInicio(becaDetalles.getFechaInicio());
            beca.setFechaFin(becaDetalles.getFechaFin());
            Beca becaActualizada = becaService.saveBeca(beca);
            return ResponseEntity.ok(becaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
