package com.cuellojuan.entity;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Cliente {


    private int id;
    private String apellido;
    private String nombre;
    private String fecha_nacimiento;
    private String tel_fijo;
    private String tel_celular;
    private String email;
    private String comentario;
    private Usuario usuarioquerecibio;
    private String fecha_insercion;


    public Cliente() {
    }

    public Cliente(int id, String apellido, String nombre, String fecha_nacimiento, String tel_fijo, String tel_celular, String email, String comentario, String fecha_insercion) throws IllegalAccessException, InstantiationException, SQLException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tel_fijo = tel_fijo;
        this.tel_celular = tel_celular;
        this.email = email;
        this.comentario = comentario;
        this.fecha_insercion = fecha_insercion;
    }


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


    public Usuario getUsuarioquerecibio() {
        return usuarioquerecibio;
    }

    public void setUsuarioquerecibio(Usuario usuarioquerecibio) {
        this.usuarioquerecibio = usuarioquerecibio;
    }

    public String getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(String fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }




}
