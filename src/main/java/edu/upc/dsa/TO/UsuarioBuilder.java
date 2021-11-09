package edu.upc.dsa.TO;

import edu.upc.dsa.models.Usuario;

public class UsuarioBuilder {
    private String name;
    private String apellido;
    private String mail;

    public UsuarioBuilder() {
    }

    public UsuarioBuilder(String name) {
        this();
        this.name = name;
    }

    public Usuario buildUsuario(){
        return new Usuario(this.name, this.apellido, this.mail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
