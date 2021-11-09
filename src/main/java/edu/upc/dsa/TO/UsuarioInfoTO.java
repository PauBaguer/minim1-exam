package edu.upc.dsa.TO;

import edu.upc.dsa.models.Usuario;

public class UsuarioInfoTO {
    private String nombre;
    private String apellido;
    private String mail;

    public UsuarioInfoTO() {
    }

    public UsuarioInfoTO(Usuario u) {
        this();
        this.nombre = u.getNombre();
        this.apellido = u.getApellido();
        this.mail = u.getMail();
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
