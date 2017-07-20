package com.cuellojuan.entity;

public class Comprainfo {

    public Integer compraid;
    public String codigoproveedor;
    public String codigoproducto;
    public String fecha;
    public int cantidad;
    public double costototal;


    public Comprainfo() {
    }

    public Comprainfo(Integer compraid, String codigoproveedor, String codigoproducto, String fecha, int cantidad, double costototal) {
        this.compraid = compraid;
        this.codigoproveedor = codigoproveedor;
        this.codigoproducto = codigoproducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.costototal = costototal;
    }


    public Integer getCompraid() {
        return compraid;
    }

    public void setCompraid(Integer compraid) {
        this.compraid = compraid;
    }

    public String getCodigoproveedor() {
        return codigoproveedor;
    }

    public void setCodigoproveedor(String codigoproveedor) {
        this.codigoproveedor = codigoproveedor;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostototal() {
        return costototal;
    }

    public void setCostototal(double costototal) {
        this.costototal = costototal;
    }



}
