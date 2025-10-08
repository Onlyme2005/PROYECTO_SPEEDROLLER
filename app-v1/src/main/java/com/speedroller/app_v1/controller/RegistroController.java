package com.speedroller.app_v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.speedroller.app_v1.model.Estudiante;
import com.speedroller.app_v1.service.EstudianteService;

@Controller
public class RegistroController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        return "index"; // Mostrar el frontend
    }

    @PostMapping("/registro")
    public String procesarRegistro(
            @RequestParam String nombre,
            @RequestParam String fechaNacimiento,
            @RequestParam String genero,
            @RequestParam String telefono,
            @RequestParam String correo,
            @RequestParam String tipoInscripcion,
            @RequestParam String metodoPago,
            Model model) {

        try {
            Estudiante estudiante = estudianteService.guardarEstudiante(
                    nombre, fechaNacimiento, genero, telefono, correo, tipoInscripcion, metodoPago, ""
            );
            model.addAttribute("mensaje", "¡Inscripción exitosa! Gracias " + estudiante.getNombre());
            model.addAttribute("exito", true);
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al registrar: " + e.getMessage());
            model.addAttribute("exito", false);
        }

        return "index"; // Regresa al mismo index con mensaje
    }
}