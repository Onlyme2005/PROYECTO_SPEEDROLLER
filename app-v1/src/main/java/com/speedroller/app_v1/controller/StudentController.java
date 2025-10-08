package com.speedroller.app_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speedroller.app_v1.model.Estudiante;
import com.speedroller.app_v1.repository.EstudianteRepository;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier cliente
public class StudentController {

    @Autowired
    private EstudianteRepository estudianteRepository; // 🔹 nombre correcto del repositorio

    // ✅ Crear un estudiante
    @PostMapping
    public Estudiante createStudent(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // ✅ Listar todos
    @GetMapping
    public List<Estudiante> getAllStudents() {
        return estudianteRepository.findAll();
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getStudentById(@PathVariable Long id) {
        return estudianteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Actualizar estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateStudent(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(estudianteDetails.getNombre());
                    estudiante.setCorreo(estudianteDetails.getCorreo());
                    estudiante.setTelefono(estudianteDetails.getTelefono());
                    estudiante.setGenero(estudianteDetails.getGenero());
                    estudiante.setTipoInscripcion(estudianteDetails.getTipoInscripcion());
                    estudiante.setMetodoPago(estudianteDetails.getMetodoPago());
                    estudiante.setFechaNacimiento(estudianteDetails.getFechaNacimiento());
                    estudiante.setFechaRegistro(estudianteDetails.getFechaRegistro());

                    Estudiante actualizado = estudianteRepository.save(estudiante);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return ResponseEntity.ok("Estudiante eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

