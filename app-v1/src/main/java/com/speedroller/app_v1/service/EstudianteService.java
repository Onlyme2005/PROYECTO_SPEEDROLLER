package com.speedroller.app_v1.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speedroller.app_v1.model.Estudiante;
import com.speedroller.app_v1.repository.EstudianteRepository;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 🔒 Para encriptar contraseñas

    /**
     * Guarda un nuevo estudiante con su información básica y contraseña encriptada.
     */
    public Estudiante guardarEstudiante(String nombre, String fechaNacimientoStr, String genero, String telefono,
                                        String correo, String tipoInscripcion, String metodoPago, String contrasena) {

        // Convertir la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);

        // Crear objeto estudiante
        Estudiante estudiante = new Estudiante(nombre, fechaNacimiento, genero, telefono, correo,
                tipoInscripcion, metodoPago, passwordEncoder.encode(contrasena), "ALUMNO");

        // 🔐 Encriptar la contraseña antes de guardarla
        estudiante.setContrasena(passwordEncoder.encode(contrasena));

        // Asignar rol por defecto
        estudiante.setRol("ALUMNO");

        // Guardar en base de datos
        return estudianteRepository.save(estudiante);
    }

    /**
     * Obtiene todos los estudiantes registrados.
     */
    public Iterable<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();

}
}
