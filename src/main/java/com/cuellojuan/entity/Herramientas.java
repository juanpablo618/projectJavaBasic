package com.cuellojuan.entity;


import java.util.Date;

public class Herramientas {

    public Integer id;
    public String fechacompra;
    public String nombre;
    public String aloja;
    public String ubicacion;


    public Herramientas() {
    }

    public Herramientas(int id, String fechacompra, String nombre, String aloja, String ubicacion) {
        this.id = id;
        this.fechacompra = fechacompra;
        this.nombre = nombre;
        this.aloja = aloja;
        this.ubicacion = ubicacion;
    }



    public String getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(String fechacompra) {
        this.fechacompra = fechacompra;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAloja() {
        return aloja;
    }

    public void setAloja(String aloja) {
        this.aloja = aloja;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
