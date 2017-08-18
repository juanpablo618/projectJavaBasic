package com.cuellojuan.entity;


public class DetalleInventario {
    private int id;
    private ElementoInventario elementoInventario;
    private  Apartamento apartamento;
    private int cantidad;

    private String fecha_insercion;
    private Usuario realizadoPor;


    public DetalleInventario() {
    }

    public DetalleInventario(int id, ElementoInventario elementoInventario, Apartamento apartamento, int cantidad, String fecha_insercion, Usuario realizadoPor) {
        this.id = id;
        this.elementoInventario = elementoInventario;
        this.apartamento = apartamento;
        this.cantidad = cantidad;
        this.fecha_insercion = fecha_insercion;
        this.realizadoPor = realizadoPor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ElementoInventario getElementoInventario() {
        return elementoInventario;
    }

    public void setElementoInventario(ElementoInventario elementoInventario) {
        this.elementoInventario = elementoInventario;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(String fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }

    public Usuario getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(Usuario realizadoPor) {
        this.realizadoPor = realizadoPor;
    }
}
