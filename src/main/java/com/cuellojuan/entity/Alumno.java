package com.cuellojuan.entity;

import java.util.Date;

public class Alumno
{

        int id;
        int legajo;
        String nombre;
        Date fecha;


    public Alumno() {
    }

    public Alumno(int id, int legajo, String nombre, Date fecha) {
        this.id = id;
        this.legajo = legajo;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLegajo()
    {
        return legajo;
    }

    public void setLegajo(int legajo)
    {
        this.legajo=legajo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha=fecha;
    }


    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                '}';
    }

}


