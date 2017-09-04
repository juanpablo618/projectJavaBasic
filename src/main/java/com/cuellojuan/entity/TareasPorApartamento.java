package com.cuellojuan.entity;


public class TareasPorApartamento {


    private int id;


    private Tarea tarea;

    private Apartamento apartamento;


    private Estado estado;


    private Usuario realizarpor;


    private String descripcion;



    public TareasPorApartamento() {

    }



    public TareasPorApartamento(int id, Tarea tarea, Apartamento apartamento, Estado estado, Usuario realizarpor, String descripcion) {
        this.id = id;
        this.tarea = tarea;
        this.apartamento = apartamento;
        this.estado = estado;
        this.realizarpor = realizarpor;
        this.descripcion = descripcion;

    }



    public int getId() {
         return id;

    }



    public void setId(int id) {
        this.id = id;

    }



    public Tarea getTarea() {
         return tarea;

    }



    public void setTarea(Tarea tarea) {
        this.tarea = tarea;

    }



    public Apartamento getApartamento() {
         return apartamento;

    }



    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;

    }



    public Estado getEstado() {
         return estado;

    }



    public void setEstado(Estado estado) {
        this.estado = estado;

    }



    public Usuario getRealizarpor() {
         return realizarpor;

    }



    public void setRealizarpor(Usuario realizarpor) {
        this.realizarpor = realizarpor;

    }



    public String getDescripcion() {
         return descripcion;

    }



    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;

    }


    @Override


    public String toString() {
         return "TareasPorApartamento{" +
                "id=" + id +
                ", tarea=" + tarea +
                ", apartamento=" + apartamento +
                ", estado=" + estado +
                ", realizarPor=" + realizarpor +
                ", descripcion='" + descripcion + '\'' +
                '}';

    }


}