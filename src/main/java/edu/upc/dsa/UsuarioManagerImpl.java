package edu.upc.dsa;

import edu.upc.dsa.TO.UsuarioInfoTO;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UsuarioManagerImpl implements UsuarioManager{
    private static UsuarioManagerImpl instance;
    private HashMap<String, Usuario> usuarioHashMap;
    private final static Logger logger = Logger.getLogger(UsuarioManagerImpl.class);

    private UsuarioManagerImpl() {
        usuarioHashMap = new HashMap<>();
    }

    public static UsuarioManagerImpl getInstance(){
        logger.info("Access singleton instance");
        if(instance==null){ instance = new UsuarioManagerImpl(); logger.info("New singleton Instance");}
        return instance;
    }

    //TODO test if user existeix a hashmap
    @Override
    public void addUser(Usuario u) {
        logger.info("Added usuario: ");
        logger.info(u);
        usuarioHashMap.put(u.getNombre(), u);
    }

    @Override
    public List<Usuario> userList_byName() {
        logger.info("userList_byName()");

        List<Usuario> l = new LinkedList<>(usuarioHashMap.values());
        l.sort(Usuario::compareTo);
        logger.info("userList_byName(): "+l);
        return l;
    }

    @Override
    public UsuarioInfoTO userInfo(String name) {
        logger.info("userInfo(" + name +")");
        Usuario u = usuarioHashMap.get(name);
        if (u==null){ logger.error("userInfo(" + name +"): User not found"); return null;}
        UsuarioInfoTO uTO = new UsuarioInfoTO(u);
        logger.info("userInfo(" + name +"): " + uTO);
        return uTO;
    }

    @Override
    public void pasarPuntoInteres(String p, String name) {
        logger.info("pasarPuntoInteres("+ p + "," + name + ")");
        Usuario u = usuarioHashMap.get(name);
        if (u==null){ logger.error("pasarPuntoInteres("+ p + "," + name + "): User not found"); return;}
        u.pasarPorPunto(p);
        logger.info("pasarPuntoInteres("+ p + "," + name + "): " + u.getPuntosInteresList());
    }

    @Override
    public List<String> puntosInteresUsuario(String nombre) {
        logger.info("puntosInteresUsuario("+nombre+")");
        Usuario u = usuarioHashMap.get(nombre);
        if (u==null){ logger.error("puntosInteresUsuario("+nombre+"): User not found"); return null;}
        logger.info("puntosInteresUsuario("+nombre+"): " + u.getPuntosInteresList());
        return u.getPuntosInteresList();
    }

    @Override
    public List<Usuario> usuariosEnPuntoInteres(String p) {
        logger.info("usuariosEnPuntoInteres(" + p +")");
        List<Usuario> l = new LinkedList<>();
        usuarioHashMap.forEach((k,v) -> {
            if(v.getPuntosInteresList().contains(p)){
                l.add(v);
            }
        });

        logger.info("usuariosEnPuntoInteres(" + p +"): " + l);
        return l;
    }

    @Override
    public List<Usuario> userList_byPuntosInteresRecorridos() {
        logger.info("userList_byPuntosInteresRecorridos()");
        List<Usuario> l = new LinkedList<>(usuarioHashMap.values());
        l.sort((Usuario u1, Usuario u2) -> Integer.compare(u2.getPuntosInteresList().size(), u1.getPuntosInteresList().size()));

        logger.info("userList_byPuntosInteresRecorridos(): " + l);
        return l;

    }

    public HashMap<String, Usuario> getUsuarioHashMap() {
        return usuarioHashMap;
    }

    public void cleanCache(){
        logger.info("cleanCache()");
        usuarioHashMap.clear();
        logger.info("cleanCache():" + usuarioHashMap);
    }
}
