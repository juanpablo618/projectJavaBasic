package com.cuellojuan.entity;



public class ElementoInventario {

    private int id;

    private String nombre;

    private String codigo;

    private String descripcion;

    private String fecha_insercion;

    private Usuario usuarioQueCompro;


    public ElementoInventario() {
    }

    public ElementoInventario(int id, String nombre, String codigo, String descripcion, String fecha_insercion, Usuario usuarioQueCompro) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha_insercion = fecha_insercion;
        this.usuarioQueCompro = usuarioQueCompro;
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

    public Usuario getUsuarioQueCompro() {
        return usuarioQueCompro;
    }

    public void setUsuarioQueCompro(Usuario usuarioQueCompro) {
        this.usuarioQueCompro = usuarioQueCompro;
    }
}
