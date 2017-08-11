package com.cuellojuan.entity;


public class Reserva {

    private int id_reserva;
    private Cliente cliente;
    private int num_personas;
    private Double tarifa_total;
    private String comentario;
    private ProvReserva proveedorDeReserva;

    public Reserva() {
    }

    public Reserva(int id_reserva, Cliente cliente, int num_personas, Double tarifa_total, String comentario, ProvReserva proveedorDeReserva) {
        this.id_reserva = id_reserva;
        this.cliente = cliente;
        this.num_personas = num_personas;
        this.tarifa_total = tarifa_total;
        this.comentario = comentario;
        this.proveedorDeReserva = proveedorDeReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
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
