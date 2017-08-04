package com.cuellojuan.entity;


public class Reserva {

    public int id_reserva;
    public int id_cliente;
    public int num_personas;
    public Double tarifa_total;
    public String comentario;
    public int id_prov_de_reserva;

    public Reserva() {
    }

    public Reserva(int id_reserva, int id_cliente, int num_personas, Double tarifa_total, String comentario, int id_prov_de_reserva) {
        this.id_reserva = id_reserva;
        this.id_cliente = id_cliente;
        this.num_personas = num_personas;
        this.tarifa_total = tarifa_total;
        this.comentario = comentario;
        this.id_prov_de_reserva = id_prov_de_reserva;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public int getId_prov_de_reserva() {
        return id_prov_de_reserva;
    }

    public void setId_prov_de_reserva(int id_prov_de_reserva) {
        this.id_prov_de_reserva = id_prov_de_reserva;
    }
}
