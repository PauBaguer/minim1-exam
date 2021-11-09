package edu.upc.dsa.TO;

import java.util.List;

public class PuntosInteresTO {
    List<String> puntosInteres;

    public PuntosInteresTO() {
    }

    public PuntosInteresTO(List<String> puntosInteres) {
        this.puntosInteres = puntosInteres;
    }

    public List<String> getPuntosInteres() {
        return puntosInteres;
    }

    public void setPuntosInteres(List<String> puntosInteres) {
        this.puntosInteres = puntosInteres;
    }
}
