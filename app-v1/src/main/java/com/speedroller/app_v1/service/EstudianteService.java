package com.speedroller.app_v1.service;

import com.speedroller.app_v1.model.Estudiante;
import com.speedroller.app_v1.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante guardarEstudiante(String nombre, String fechaNacimientoStr, String genero, String telefono,
                                        String correo, String tipoInscripcion, String metodoPago) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);

        Estudiante estudiante = new Estudiante(nombre, fechaNacimiento, genero, telefono, correo,
                tipoInscripcion, metodoPago);

        return estudianteRepository.save(estudiante);
    }

    public Iterable<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }
}