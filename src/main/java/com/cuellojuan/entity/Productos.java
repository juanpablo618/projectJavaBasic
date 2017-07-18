package com.cuellojuan.entity;

public class Productos {

    public Integer pid;
    public String productocodigo;
    public String productonombre;
    public double preciocosto;
    public double precioventa;
    public String marca;


    public Productos() {
    }


    public Productos(Integer pid, String productocodigo, String productonombre, double preciocosto, double precioventa, String marca) {
        this.pid = pid;
        this.productocodigo = productocodigo;
        this.productonombre = productonombre;
        this.preciocosto = preciocosto;
        this.precioventa = precioventa;
        this.marca = marca;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProductocodigo() {
        return productocodigo;
    }

    public void setProductocodigo(String productocodigo) {
        this.productocodigo = productocodigo;
    }

    public String getProductonombre() {
        return productonombre;
    }

    public void setProductonombre(String productonombre) {
        this.productonombre = productonombre;
    }

    public double getPreciocosto() {
        return preciocosto;
    }

    public void setPreciocosto(double preciocosto) {
        this.preciocosto = preciocosto;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
