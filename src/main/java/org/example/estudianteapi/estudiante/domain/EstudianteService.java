package org.example.estudianteapi.estudiante.domain;

import org.example.estudianteapi.beca.domain.Beca;
import org.example.estudianteapi.beca.domain.BecaService;
import org.example.estudianteapi.estudiante.domain.Estudiante;
import org.example.estudianteapi.estudiante.infrastructure.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private BecaService becaService;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante getEstudianteById(Long id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public Estudiante.EscalaPago getEscalaPagoByCorreo(String correo) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findByCorreo(correo);
        if (estudianteOpt.isPresent()) {
            return estudianteOpt.get().getEscalaPago();
        } else {
            throw new RuntimeException("Estudiante no encontrado con el correo: " + correo);
        }
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return estudianteRepository.existsById(id);
    }

    public Optional<Estudiante> obtenerEstudiantePorCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

    public Estudiante assignBecaToEstudiante(Long estudianteId, Long becaId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Beca beca = becaService.getBecaById(becaId);
        if (beca == null) {
            throw new RuntimeException("Beca no encontrada");
        }

        estudiante.setBeca(beca);
        return estudianteRepository.save(estudiante);
    }

}
