package com.cuellojuan.entity;


public class Reserva {

    private int id;
    private Cliente idCliente;
    private int num_personas;
    private Double tarifa_total;
    private String comentario;
    private ProvReserva proveedorDeReserva;

    public Reserva() {
    }

    public Reserva(int id, Cliente cliente, int num_personas, Double tarifa_total, String comentario, ProvReserva proveedorDeReserva) {
        this.id = id;
        this.num_personas = num_personas;
        this.tarifa_total = tarifa_total;
        this.comentario = comentario;
        this.proveedorDeReserva = proveedorDeReserva;
    }

    public Cliente getCliente() {
        return idCliente;
    }

    public void setCliente(Cliente cliente) {
        this.idCliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(int num_personas) {
        this.num_personas = num_personas;
    }

    public Double getTarifa_total() {
        return tarifa_total;
    }

    public void setTarifa_total(Double tarifa_total) {
        this.tarifa_total = tarifa_total;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ProvReserva getProveedorDeReserva() {
        return proveedorDeReserva;
    }

    public void setProveedorDeReserva(ProvReserva proveedorDeReserva) {
        this.proveedorDeReserva = proveedorDeReserva;
    }
}
