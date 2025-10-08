package com.speedroller.app_v1.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // Ejemplo de valores: "ROLE_USER", "ROLE_ADMIN"
    @Column(unique = true, nullable = false)
    private String nameString;

    // =======================
    // Constructores
    // =======================
    public Role() {}

    public Role(Long id, String nameString) {
        this.id = id;
        this.nameString = nameString;
    }

    // =======================
    // Getters y Setters
    // =======================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameString() {
        return nameString;
    }

    public void setName(String nameString) {
        this.nameString = nameString;
    }

    // =======================
    // equals y hashCode
    // =======================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(nameString, role.nameString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameString);
    }

    // =======================
    // toString
    // =======================
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nombre='" + nameString + '\'' +
                '}';
    }
}
