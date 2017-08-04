package com.cuellojuan.entity;

public class Cliente {


    public int id_cliente;
    public String apellido;
    public String nombre;
    public String fecha_nacimiento;
    public String tel_fijo;
    public String tel_celular;
    public String email;
    public String comentario;
    public int recibido_por;
    public String fecha_insercion;


    public Cliente() {
    }

    public Cliente(int id_cliente, String apellido, String nombre, String fecha_nacimiento, String tel_fijo, String tel_celular, String email, String comentario, int recibido_por, String fecha_insercion) {
        this.id_cliente = id_cliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tel_fijo = tel_fijo;
        this.tel_celular = tel_celular;
        this.email = email;
        this.comentario = comentario;
        this.recibido_por = recibido_por;
        this.fecha_insercion = fecha_insercion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public int getRecibido_por() {
        return recibido_por;
    }

    public void setRecibido_por(int recibido_por) {
        this.recibido_por = recibido_por;
    }

    public String getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(String fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }
}
