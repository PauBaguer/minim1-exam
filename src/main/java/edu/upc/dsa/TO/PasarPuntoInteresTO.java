package edu.upc.dsa.TO;

public class PasarPuntoInteresTO {
    private String nombre;
    private String puntoInteres;

    public PasarPuntoInteresTO() {
    }

    public PasarPuntoInteresTO(String nombre, String puntoInteres) {
        this();
        this.nombre = nombre;
        this.puntoInteres = puntoInteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntoInteres() {
        return puntoInteres;
    }

    public void setPuntoInteres(String puntoInteres) {
        this.puntoInteres = puntoInteres;
    }
}
