package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Usuario implements Comparable<Usuario>{
    private String nombre;
    private String apellido;
    private String mail;
    private List<String> puntosInteresList;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String mail) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
