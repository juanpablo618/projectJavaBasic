package com.cuellojuan.entity;


public class Apartamento {

    private int id;
    private int nro_piso;
    private int ocupacion_maxima;
    private String nombre_edificio;
    private String comentario;


    public Apartamento() {
    }

    public Apartamento(int id, int nro_piso, int ocupacion_maxima, String nombre_edificio, String comentario) {
        this.id = id;
        this.nro_piso = nro_piso;
        this.ocupacion_maxima = ocupacion_maxima;
        this.nombre_edificio = nombre_edificio;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Apartamento{" +
                "id=" + id +
                ", nro_piso=" + nro_piso +
                ", ocupacion_maxima=" + ocupacion_maxima +
                ", nombre_edificio='" + nombre_edificio + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
