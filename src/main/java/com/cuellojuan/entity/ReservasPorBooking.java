package com.cuellojuan.entity;


public class ReservasPorBooking {

    //id cliente
    private int idCliente;
    //id de la pieza donde se aloja
    private int idApartamento;
    //nro de personas de la reserva
    private int numPersonas;
    //tarifa total
    private double tarifaTotal;



    public ReservasPorBooking() {
    }


    public ReservasPorBooking(int idCliente, int idApartamento, int numPersonas, double tarifaTotal) {
        this.idCliente = idCliente;
        this.idApartamento = idApartamento;
        this.numPersonas = numPersonas;
        this.tarifaTotal = tarifaTotal;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(int idApartamento) {
        this.idApartamento = idApartamento;
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
        return "ReservasPorBooking{" +
                "idCliente=" + idCliente +
                ", idApartamento=" + idApartamento +
                ", numPersonas=" + numPersonas +
                ", tarifaTotal=" + tarifaTotal +
                '}';
    }
}
