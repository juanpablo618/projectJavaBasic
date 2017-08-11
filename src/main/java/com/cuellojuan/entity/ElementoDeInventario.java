package com.cuellojuan.entity;


import java.util.Date;

public class ElementoDeInventario {

    private int id_elemento_de_inventario;

    private String nombre;

    private String codigo;

    private String descripcion;

    private Date fecha_insercion;

    private int id_usuario;


    public ElementoDeInventario() {
    }

    public ElementoDeInventario(int id_elemento_de_inventario, String nombre, String codigo, String descripcion, Date fecha_insercion, int id_usuario) {
        this.id_elemento_de_inventario = id_elemento_de_inventario;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha_insercion = fecha_insercion;
        this.id_usuario = id_usuario;
    }


    public int getId_elemento_de_inventario() {
        return id_elemento_de_inventario;
    }

    public void setId_elemento_de_inventario(int id_elemento_de_inventario) {
        this.id_elemento_de_inventario = id_elemento_de_inventario;
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

    public Date getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(Date fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
