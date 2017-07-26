package com.cuellojuan.entity;


public class ClientesPorBooking {

    //id cliente
    private int idCliente;
    //id de la pieza donde se aloja
    private int idPieza;
    //nro de personas de la reserva
    private int numPersonas;
    //tarifa total
    private double tarifaTotal;



    public ClientesPorBooking() {
    }


    public ClientesPorBooking(int idCliente, int idPieza, int numPersonas, double tarifaTotal) {
        this.idCliente = idCliente;
        this.idPieza = idPieza;
        this.numPersonas = numPersonas;
        this.tarifaTotal = tarifaTotal;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public double getTarifaTotal() {
        return tarifaTotal;
    }

    public void setTarifaTotal(double tarifaTotal) {
        this.tarifaTotal = tarifaTotal;
    }


    @Override
    public String toString() {
        return "ClientesPorBooking{" +
                "idCliente=" + idCliente +
                ", idPieza=" + idPieza +
                ", numPersonas=" + numPersonas +
                ", tarifaTotal=" + tarifaTotal +
                '}';
    }
}
