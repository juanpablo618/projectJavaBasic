package com.cuellojuan.entity;



import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nombre;
    private String apellido;

    List<Cliente> clientesAlosQueRecibio = new ArrayList<>();
    List<Tarea> tareasARealizar = new ArrayList<>();
    List<Servicio> servicioARealizar = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public List<Cliente> getClientesAlosQueRecibio() {
        return clientesAlosQueRecibio;
    }

    public void setClientesAlosQueRecibio(List<Cliente> clientesAlosQueRecibio) {
        this.clientesAlosQueRecibio = clientesAlosQueRecibio;
    }

    public List<Tarea> getTareasARealizar() {
        return tareasARealizar;
    }

    public void setTareasARealizar(List<Tarea> tareasARealizar) {
        this.tareasARealizar = tareasARealizar;
    }

    public List<Servicio> getServicioARealizar() {
        return servicioARealizar;
    }

    public void setServicioARealizar(List<Servicio> servicioARealizar) {
        this.servicioARealizar = servicioARealizar;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", clientesAlosQueRecibio=" + clientesAlosQueRecibio +
                ", tareasARealizar=" + tareasARealizar +
                ", servicioARealizar=" + servicioARealizar +
                '}';
    }
}
