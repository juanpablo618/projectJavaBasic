package com.cuellojuan.entity;

public class Tarea {

    private int id;
    private String nombre;
    private String descripcion;
    private Estado estado;
    private Usuario realizador;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getRealizador() {
        return realizador;
    }

    public void setRealizador(Usuario realizador) {
        this.realizador = realizador;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", realizador=" + realizador +
                '}';
    }
}
