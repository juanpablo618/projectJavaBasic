package com.cuellojuan.entity;

public class Productos {

    public Integer id;
    public String productocodigo;
    public String productonombre;
    public double preciocosto;
    public double precioventa;
    public String marca;
    public int stockactual;


    public Productos() {
    }


    public Productos(Integer id, String productocodigo, String productonombre, double preciocosto, double precioventa, String marca, int stockactual) {
        this.id = id;
        this.productocodigo = productocodigo;
        this.productonombre = productonombre;
        this.preciocosto = preciocosto;
        this.precioventa = precioventa;
        this.marca = marca;
        this.stockactual = stockactual;
    }


    public int getStockactual() {
        return stockactual;
    }

    public void setStockactual(int stockactual) {
        this.stockactual = stockactual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
