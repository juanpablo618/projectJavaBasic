package com.cuellojuan.entity;


public class TareasPorApartamento {

    private int id;

    private Tarea tarea;
    private Apartamento apartamento;

    private Estado estado;

    private Usuario realizarPor;

    private String descripcion;

    public TareasPorApartamento() {
    }

    public TareasPorApartamento(int id, Tarea tarea, Apartamento apartamento, Estado estado, Usuario realizarPor, String descripcion) {
        this.id = id;
        this.tarea = tarea;
        this.apartamento = apartamento;
        this.estado = estado;
        this.realizarPor = realizarPor;
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

    public Usuario getRealizarPor() {
        return realizarPor;
    }

    public void setRealizarPor(Usuario realizarPor) {
        this.realizarPor = realizarPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
