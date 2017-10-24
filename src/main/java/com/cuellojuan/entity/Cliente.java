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
    private Usuario usuarioquerecibio;
    private String fecha_insercion;
    private Reserva reservaAsociada;


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

    public Reserva getReservaAsociada() {
        return reservaAsociada;
    }

    public void setReservaAsociada(Reserva reservaAsociada) {
        this.reservaAsociada = reservaAsociada;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", tel_fijo='" + tel_fijo + '\'' +
                ", tel_celular='" + tel_celular + '\'' +
                ", email='" + email + '\'' +
                ", comentario='" + comentario + '\'' +
                ", usuarioquerecibio=" + usuarioquerecibio +
                ", fecha_insercion='" + fecha_insercion + '\'' +
                ", reservaAsociada=" + reservaAsociada +
                '}';
    }
}
