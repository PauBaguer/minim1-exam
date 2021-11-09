package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Usuario implements Comparable<Usuario>{
    private String nombre;
    private List<String> puntosInteresList;

    public Usuario() {
    }

    public Usuario(String nombre) {
        this();
        this.nombre = nombre;
        this.puntosInteresList = new LinkedList<>();
    }

    public void pasarPorPunto(String p){
        puntosInteresList.add(p);
    }

    @Override
    public int compareTo(Usuario o) {
        return this.nombre.compareTo(o.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getPuntosInteresList() {
        return puntosInteresList;
    }

    public void setPuntosInteresList(List<String> puntosInteresList) {
        this.puntosInteresList = puntosInteresList;
    }
}
