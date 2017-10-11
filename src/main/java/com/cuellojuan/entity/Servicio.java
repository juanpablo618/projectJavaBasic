package com.cuellojuan.entity;


import java.util.ArrayList;
import java.util.List;

public class Servicio {


    private int id;
    private String nombre;
    private String codigo;
    private String descripcion;

    private Estado estado;
    private Usuario vendidoPor;


    public Servicio() {
    }


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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Usuario getVendidoPor() {
        return vendidoPor;
    }

    public void setVendidoPor(Usuario vendidoPor) {
        this.vendidoPor = vendidoPor;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", vendidoPor=" + vendidoPor +
                '}';
    }
}
