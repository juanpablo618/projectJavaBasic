package com.cuellojuan.entity;


import java.util.ArrayList;
import java.util.List;

public class ElementoInventario {

    private int id;

    private String nombre;

    private String codigo;

    private String descripcion;

    private String fecha_insercion;

    private Usuario usuarioquecompro;


    public ElementoInventario() {
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

    public String getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(String fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioquecompro() {
        return usuarioquecompro;
    }

    public void setUsuarioquecompro(Usuario usuarioquecompro) {
        this.usuarioquecompro = usuarioquecompro;
    }

    @Override
    public String toString() {
        return "ElementoInventario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_insercion='" + fecha_insercion + '\'' +
                ", usuarioquecompro=" + usuarioquecompro +
                '}';
    }
}
