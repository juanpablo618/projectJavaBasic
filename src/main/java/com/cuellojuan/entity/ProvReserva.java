package com.cuellojuan.entity;


public class ProvReserva {

    private int id_prov_de_reserva;
    private String nombre;
    private String descripcion;

    public ProvReserva() {
    }

    public ProvReserva(int id_prov_de_reserva, String nombre, String descripcion) {
        this.id_prov_de_reserva = id_prov_de_reserva;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_prov_de_reserva() {
        return id_prov_de_reserva;
    }

    public void setId_prov_de_reserva(int id_prov_de_reserva) {
        this.id_prov_de_reserva = id_prov_de_reserva;
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
}
