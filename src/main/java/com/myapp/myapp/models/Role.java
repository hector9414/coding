package com.myapp.myapp.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Permiso> permisos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
