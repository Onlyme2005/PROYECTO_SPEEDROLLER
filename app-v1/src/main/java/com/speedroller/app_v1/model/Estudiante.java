package com.speedroller.app_v1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String tipoInscripcion; // "ALUMNO", "INSTRUCTOR", etc.

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private String contraseña; // 🔐 Nueva columna para login

    @Column(nullable = false)
    private String rol = "ALUMNO"; // 🧩 Rol por defecto

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    // 🔧 Constructor vacío (requerido por JPA)
    public Estudiante() {}

    // 💡 Constructor con parámetros
    public Estudiante(String nombre, LocalDate fechaNacimiento, String genero, String telefono, String correo,
                        String tipoInscripcion, String metodoPago, String contraseña, String rol) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoInscripcion = tipoInscripcion;
        this.metodoPago = metodoPago;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // 🕒 Asigna fecha automáticamente antes de guardar
    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTipoInscripcion() { return tipoInscripcion; }
    public void setTipoInscripcion(String tipoInscripcion) { this.tipoInscripcion = tipoInscripcion; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    // 🔐 Métodos para manejar la contraseña de forma segura

    private String contrasena;

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }
}
    
