package org.example.estudianteapi.estudiante.domain;

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

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante getEstudianteById(Long id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Optional<Estudiante> obtenerEstudiantePorCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

}
