package com.cuellojuan.entity;

import java.util.ArrayList;
import java.util.List;

public class Apartamento {

    private int id;
    private int nro_piso;
    private int ocupacion_maxima;
    private String nombre_edificio;
    private String comentario;
    private Reserva reserva;

    private List<Servicio> serviciosBrindados = new ArrayList<>();
    private List<Tarea> tareasARealizarle = new ArrayList<>();
    private List<ElementoInventario> elementosDeInventarioQuePosee = new ArrayList<>();


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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Tarea> getTareasARealizarle() {
        return tareasARealizarle;
    }

    public void setTareasARealizarle(List<Tarea> tareasARealizarle) {
        this.tareasARealizarle = tareasARealizarle;
    }

    public List<Servicio> getServiciosBrindados() {
        return serviciosBrindados;
    }

    public void setServiciosBrindados(List<Servicio> serviciosBrindados) {
        this.serviciosBrindados = serviciosBrindados;
    }

    public List<ElementoInventario> getElementosDeInventarioQuePosee() {
        return elementosDeInventarioQuePosee;
    }

    public void setElementosDeInventarioQuePosee(List<ElementoInventario> elementosDeInventarioQuePosee) {
        this.elementosDeInventarioQuePosee = elementosDeInventarioQuePosee;
    }

    @Override
    public String toString() {
        return "Apartamento{" +
                "id=" + id +
                ", nro_piso=" + nro_piso +
                ", ocupacion_maxima=" + ocupacion_maxima +
                ", nombre_edificio='" + nombre_edificio + '\'' +
                ", comentario='" + comentario + '\'' +
                ", reserva=" + reserva +
                ", tareasARealizarle=" + tareasARealizarle +
                ", serviciosBrindados=" + serviciosBrindados +
                ", elementosDeInventarioQuePosee=" + elementosDeInventarioQuePosee +
                '}';
    }
}
