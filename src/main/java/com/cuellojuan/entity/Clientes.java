package com.cuellojuan.entity;

public class Clientes {


    public Integer cid;
    public String clientecodigo;
    public String nombre;
    public String ubicacion;
    public String telefono;

    public Clientes() {
    }

    public Clientes(Integer cid, String clientecodigo, String nombre, String ubicacion, String telefono) {
        this.cid = cid;
        this.clientecodigo = clientecodigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getClientecodigo() {
        return clientecodigo;
    }

    public void setClientecodigo(String clientecodigo) {
        this.clientecodigo = clientecodigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
