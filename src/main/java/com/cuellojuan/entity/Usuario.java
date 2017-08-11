package com.cuellojuan.entity;


public class Usuario {

    private int id_usuario;
    private String nombre;
    private String apellido;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre, String apellido) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
    }


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    public int devolverID(){

        return id_usuario;
    }

}
