package com.cuellojuan.entity;


public class Reportesventas {

    public Integer ventasid;
    public String fecha;
    public String codigoproducto;
    public String codigocliente;
    public int cantidad;
    public double ingresos;
    public String vendidopor;

    public Reportesventas() {
    }


    public Reportesventas(Integer ventasid, String fecha, String codigoproducto, String codigocliente, int cantidad, double ingresos, String vendidopor) {
        this.ventasid = ventasid;
        this.fecha = fecha;
        this.codigoproducto = codigoproducto;
        this.codigocliente = codigocliente;
        this.cantidad = cantidad;
        this.ingresos = ingresos;
        this.vendidopor = vendidopor;
    }

    public Integer getVentasid() {
        return ventasid;
    }

    public void setVentasid(Integer ventasid) {
        this.ventasid = ventasid;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getCodigocliente() {
        return codigocliente;
    }

    public void setCodigocliente(String codigocliente) {
        this.codigocliente = codigocliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public String getVendidopor() {
        return vendidopor;
    }

    public void setVendidopor(String vendidopor) {
        this.vendidopor = vendidopor;
    }

    @Override
    public String toString() {
        return "Reportesventas{" +
                "ventasid=" + ventasid +
                ", fecha='" + fecha + '\'' +
                ", codigoproducto='" + codigoproducto + '\'' +
                ", codigocliente='" + codigocliente + '\'' +
                ", cantidad=" + cantidad +
                ", ingresos=" + ingresos +
                ", vendidopor='" + vendidopor + '\'' +
                '}';
    }
}
