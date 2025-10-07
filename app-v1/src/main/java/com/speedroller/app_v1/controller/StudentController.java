package com.speedroller.app_v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    private EstudianteRepository studentRepository;

    // ✅ Crear un estudiante
    @PostMapping
    public Estudiante createStudent(@RequestBody Estudiante student) {
        return studentRepository.save(student);
    }

    // ✅ Listar todos
    @GetMapping
    public List<Estudiante> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public Optional<Estudiante> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    // ✅ Actualizar
    @PutMapping("/{id}")
    public Estudiante updateStudent(@PathVariable Long id, @RequestBody Estudiante studentDetails) {
        Estudiante student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        student.setNombre(studentDetails.getNombre());
        student.setId(studentDetails.getId());
        student.setCorreo(studentDetails.getCorreo());
        return studentRepository.save(student);
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "Estudiante eliminado correctamente";
    }
}
