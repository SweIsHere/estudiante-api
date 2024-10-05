package org.example.estudianteapi.beca.application;

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

    @GetMapping
    public List<Beca> getAllBecas() {
        return becaService.getAllBecas();
    }

    @PostMapping
    public Beca createBeca(@RequestBody Beca beca) {
        return becaService.saveBeca(beca);
    }

    @GetMapping("/{id}")
    public Beca getBecaById(@PathVariable Long id) {
        return becaService.getBecaById(id);
    }

    @DeleteMapping("/becas/{id}")
    public ResponseEntity<Void> deleteBeca(@PathVariable Long id) {
        try {
            becaService.deleteBeca(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
