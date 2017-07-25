package com.cuellojuan.entity;


public class Piezas {
    private int ID;
    private String nombreCasa;

    public Piezas() {
    }

    public Piezas(int ID, String nombreCasa) {
        this.ID = ID;
        this.nombreCasa = nombreCasa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreCasa() {
        return nombreCasa;
    }

    public void setNombreCasa(String nombreCasa) {
        this.nombreCasa = nombreCasa;
    }

    @Override
    public String toString() {
        return "Pieza{" +
                "ID=" + ID +
                '}';
    }
}
