package com.cuellojuan.entity;


public class DetalleInventario {
    private int id;
    private ElementoInventario elementoinventario;
    private  Apartamento apartamento;
    private int cantidad;

    private String fecha_insercion;
    private Usuario realizadopor;


    public DetalleInventario() {
    }

    public DetalleInventario(int id, ElementoInventario elementoinventario, Apartamento apartamento, int cantidad, String fecha_insercion, Usuario realizadopor) {
        this.id = id;
        this.elementoinventario = elementoinventario;
        this.apartamento = apartamento;
        this.cantidad = cantidad;
        this.fecha_insercion = fecha_insercion;
        this.realizadopor = realizadopor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ElementoInventario getElementoinventario() {
        return elementoinventario;
    }

    public void setElementoinventario(ElementoInventario elementoinventario) {
        this.elementoinventario = elementoinventario;
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

    public Usuario getRealizadopor() {
        return realizadopor;
    }

    public void setRealizadopor(Usuario realizadopor) {
        this.realizadopor = realizadopor;
    }

    @Override
    public String toString() {
        return "DetalleInventario{" +
                "id=" + id +
                ", elementoinventario=" + elementoinventario +
                ", apartamento=" + apartamento +
                ", cantidad=" + cantidad +
                ", fecha_insercion='" + fecha_insercion + '\'' +
                ", realizadoPor=" + realizadopor +
                '}';
    }
}
