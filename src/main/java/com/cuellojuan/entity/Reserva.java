package com.cuellojuan.entity;


import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private int id;
    private Cliente cliente;
    private int num_personas;
    private Double tarifa_total;
    private String comentario;
    private ProvReserva proveedor;

    private List<Apartamento>  apartPorReserva = new ArrayList<>();


    public List<Apartamento> getApartPorReserva() {
        return apartPorReserva;
    }

    public void setApartPorReserva(List<Apartamento> apartPorReserva) {
        this.apartPorReserva = apartPorReserva;
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

    public ProvReserva getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProvReserva proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", num_personas=" + num_personas +
                ", tarifa_total=" + tarifa_total +
                ", comentario='" + comentario + '\'' +
                ", proveedor=" + proveedor +
                ", apartPorReserva=" + apartPorReserva +
                '}';
    }
}
