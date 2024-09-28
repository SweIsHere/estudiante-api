package org.example.estudianteapi.beca.application;

import org.example.estudianteapi.beca.domain.Beca;
import org.example.estudianteapi.beca.domain.BecaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public void deleteBeca(@PathVariable Long id) {
        becaService.deleteBeca(id);
    }
}
