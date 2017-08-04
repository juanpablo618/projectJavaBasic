package com.cuellojuan.entity;


public class Apartamento {

    public int id_apartamento;
    public int nro_piso;
    public int ocupacion_maxima;
    public String nombre_edificio;
    public String comentario;


    public Apartamento() {
    }

    public Apartamento(int id_apartamento, int nro_piso, int ocupacion_maxima, String nombre_edificio, String comentario) {
        this.id_apartamento = id_apartamento;
        this.nro_piso = nro_piso;
        this.ocupacion_maxima = ocupacion_maxima;
        this.nombre_edificio = nombre_edificio;
        this.comentario = comentario;
    }

    public int getId_apartamento() {
        return id_apartamento;
    }

    public void setId_apartamento(int id_apartamento) {
        this.id_apartamento = id_apartamento;
    }

    public int getNro_piso() {
        return nro_piso;
    }

    public void setNro_piso(int nro_piso) {
        this.nro_piso = nro_piso;
    }

    public int getOcupacion_maxima() {
        return ocupacion_maxima;
    }

    public void setOcupacion_maxima(int ocupacion_maxima) {
        this.ocupacion_maxima = ocupacion_maxima;
    }

    public String getNombre_edificio() {
        return nombre_edificio;
    }

    public void setNombre_edificio(String nombre_edificio) {
        this.nombre_edificio = nombre_edificio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
