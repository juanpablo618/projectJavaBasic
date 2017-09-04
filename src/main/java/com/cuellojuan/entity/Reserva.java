package com.cuellojuan.entity;


import java.util.List;

public class Reserva {

    private int id;
    private Cliente cliente;
    private int num_personas;
    private Double tarifa_total;
    private String comentario;
    private ProvReserva proveedorreserva;

    public Reserva() {
    }

    public Reserva(int id,  int num_personas, Double tarifa_total, String comentario, ProvReserva proveedorreserva) {
        this.id = id;
        this.num_personas = num_personas;
        this.tarifa_total = tarifa_total;
        this.comentario = comentario;
        this.proveedorreserva = proveedorreserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public ProvReserva getProveedorreserva() {
        return proveedorreserva;
    }

    public void setProveedorreserva(ProvReserva proveedorreserva) {
        this.proveedorreserva = proveedorreserva;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", num_personas=" + num_personas +
                ", tarifa_total=" + tarifa_total +
                ", comentario='" + comentario + '\'' +
                ", proveedorreserva=" + proveedorreserva +
                '}';
    }
}
