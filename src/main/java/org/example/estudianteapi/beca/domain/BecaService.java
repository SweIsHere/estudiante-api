package org.example.estudianteapi.beca.domain;

import org.example.estudianteapi.beca.domain.Beca;
import org.example.estudianteapi.beca.infrastructure.BecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BecaService {

    @Autowired
    private BecaRepository becaRepository;

    public List<Beca> getAllBecas() {
        return becaRepository.findAll();
    }

    public Beca saveBeca(Beca beca) {
        return becaRepository.save(beca);
    }

    public Beca getBecaById(Long id) {
        return becaRepository.findById(id).orElseThrow(() -> new RuntimeException("Beca no encontrada"));
    }

    public void deleteBeca(Long id) {
        if (becaRepository.existsById(id)) {
            becaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Beca no encontrada con el id: " + id);
        }
    }
}
