package com.speedroller.app_v1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clase")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia", nullable = false)
    private String dia;

    @Column(name = "hora", nullable = false)
    private String hora;

    @Column(name = "nivel", nullable = false)
    private String nivel;

    @Column(name = "instructor", nullable = false)
    private String instructor;

    @Column(name = "capacidad_maxima", nullable = false)
    private int capacidadMaxima;

    @Column(name = "capacidad_actual")
    private int capacidadActual = 0;

    // Constructor vacío
    public Clase() {}

    public Clase(String dia, String hora, String nivel, String instructor, int capacidadMaxima) {
        this.dia = dia;
        this.hora = hora;
        this.nivel = nivel;
        this.instructor = instructor;
        this.capacidadMaxima = capacidadMaxima;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public int getCapacidadActual() { return capacidadActual; }
    public void setCapacidadActual(int capacidadActual) { this.capacidadActual = capacidadActual; }
}