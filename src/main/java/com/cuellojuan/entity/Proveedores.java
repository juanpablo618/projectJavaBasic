package com.cuellojuan.entity;


public class Proveedores {

    public Integer pid;
    public String codigoproveedor;
    public String nombrecompleto;
    public String ubicacion;
    public String telefono;


    public Proveedores() {
    }


    public Proveedores(Integer pid, String codigoproveedor, String nombrecompleto, String ubicacion, String telefono) {
        this.pid = pid;
        this.codigoproveedor = codigoproveedor;
        this.nombrecompleto = nombrecompleto;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCodigoproveedor() {
        return codigoproveedor;
    }

    public void setCodigoproveedor(String codigoproveedor) {
        this.codigoproveedor = codigoproveedor;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
