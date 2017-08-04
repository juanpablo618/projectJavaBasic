package com.cuellojuan.entity;


public class Servicio {


    public String id_servicio;
    public String nombre;
    public String codigo;
    public String descripcion;

    public Servicio() {
    }


    public Servicio(String id_servicio, String nombre, String codigo, String descripcion) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
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
}
