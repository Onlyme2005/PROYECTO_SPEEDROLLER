package com.speedroller.app_v1.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "tipo_inscripcion", nullable = false)
    private String tipoInscripcion; // "ESTUDIANTE" o "INSTRUCTOR"

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago; // "TARJETA", "EFECTIVO", "TRANSFERENCIA"

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Constructor vacío (obligatorio para JPA)
    public Estudiante() {}

    // Constructor con parámetros
    public Estudiante(String nombre, LocalDate fechaNacimiento, String genero, String telefono, String correo,
                      String tipoInscripcion, String metodoPago) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoInscripcion = tipoInscripcion;
        this.metodoPago = metodoPago;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters
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

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}