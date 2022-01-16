package com.example.amstairflumtest.menu.Clases;

public class Clases {
    String claseid,nombreDispo, nombre,direccion, telefono, capacidad;

    public Clases(String claseid, String nombreDispo, String nombre, String direccion, String telefono, String capacidad) {
        this.claseid = claseid;
        this.nombreDispo = nombreDispo;
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

    public String getNombreDispo() {
        return nombreDispo;
    }

    public void setNombreDispo(String nombreDispo) {
        this.nombreDispo = nombreDispo;
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
