package com.example.amstairflumtest.menu.Clases;

public class Clases {
    String claseid, nombre,direccion, telefono, capacidad;

    public Clases(String claseid, String nombre, String direccion, String telefono, String capacidad) {
        this.claseid = claseid;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.capacidad = capacidad;
    }

    public String getClaseid() {
        return claseid;
    }

    public void setClaseid(String claseid) {
        this.claseid = claseid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}
