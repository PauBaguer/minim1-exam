package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface UsuarioManager {
    void addUser(Usuario u);
    List<Usuario> userList_byName();
    Usuario userInfo(String name);
    void pasarPuntoInteres(String p, String name);
    List<String> puntosInteresUsuario(String nombre);
    List<Usuario> usuariosEnPuntoInteres(String p);
    List<Usuario> userList_byPuntosInteresRecorridos();
}
