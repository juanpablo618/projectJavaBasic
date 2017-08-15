package com.cuellojuan.entity;


public class Servicio {


    private String id;
    private String nombre;
    private String codigo;
    private String descripcion;

    public Servicio() {
    }


    public Servicio(String id, String nombre, String codigo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id_servicio) {
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
}
