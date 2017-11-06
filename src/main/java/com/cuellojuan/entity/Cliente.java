package com.cuellojuan.entity;


public class Cliente {

    private int id;
    private String apellido;
    private String nombre;
    private String fecha_nacimiento;
    private String tel_fijo;
    private String tel_celular;
    private String email;
    private String comentario;
    private Usuario receptor;
    private String fecha_insercion;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTel_fijo() {
        return tel_fijo;
    }

    public void setTel_fijo(String tel_fijo) {
        this.tel_fijo = tel_fijo;
    }

    public String getTel_celular() {
        return tel_celular;
    }

    public void setTel_celular(String tel_celular) {
        this.tel_celular = tel_celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public String getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(String fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fecha_nacimiento + '\'' +
                ", telFijo='" + tel_fijo + '\'' +
                ", telCelular='" + tel_celular + '\'' +
                ", email='" + email + '\'' +
                ", comentario='" + comentario + '\'' +
                ", receptor=" + receptor +
                ", fechaInsercion='" + fecha_insercion + '\'' +
                '}';
    }
}