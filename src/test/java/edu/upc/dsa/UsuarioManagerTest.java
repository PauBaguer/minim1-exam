package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UsuarioManagerTest {

    Usuario u1;
    Usuario u2;
    Usuario u3;
    Usuario u4;
//TODO TEST ERROR MESSAGES, AND ADD EXCEPTIONS, FATAL??
    @Before
    public void setUp(){
        u1 = new Usuario("Pau");
        u2 = new Usuario("Anna");
        u3 = new Usuario("Maria");
        u4 = new Usuario("Jana");


        UsuarioManagerImpl.getInstance().addUser(u1);
        UsuarioManagerImpl.getInstance().addUser(u2);
        UsuarioManagerImpl.getInstance().addUser(u3);
        UsuarioManagerImpl.getInstance().addUser(u4);
    }

    @Test
    public void testUserList_byName(){
        Usuario[] arr = {u2, u4, u3, u1};

        List<Usuario> l = UsuarioManagerImpl.getInstance().userList_byName();
        Assert.assertArrayEquals(l.toArray(), arr);
    }

    @Test
    public void testPassarPuntoInteres(){
        //Tambien testea userInfo()

        String name = "Pau";
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", name);
        String[] arr = {"CasillaA"};

        Assert.assertArrayEquals(UsuarioManagerImpl.getInstance().userInfo(name).getPuntosInteresList().toArray(), arr);

        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", name);
        String[] arr2 = {"CasillaA", "CasillaB"};
        Assert.assertArrayEquals(UsuarioManagerImpl.getInstance().userInfo(name).getPuntosInteresList().toArray(), arr2);

    }

    @Test
    public void testPuntosInteresUsuario(){
        String name = "Pau";
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", name);
        String[] arr = {"CasillaA"};

        Assert.assertArrayEquals(UsuarioManagerImpl.getInstance().puntosInteresUsuario(name).toArray(), arr);

        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", name);
        String[] arr2 = {"CasillaA", "CasillaB"};
        Assert.assertArrayEquals(UsuarioManagerImpl.getInstance().puntosInteresUsuario(name).toArray(), arr2);
    }

    @Test
    public void testUsuariosEnPuntoInteres(){
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", "Pau");
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", "Pau");
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaC", "Pau");

        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", "Jana");

        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", "Anna");
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaC", "Anna");

        Usuario[] arr = {u1, u2};
        Assert.assertArrayEquals(UsuarioManagerImpl.getInstance().usuariosEnPuntoInteres("CasillaC").toArray(), arr);
    }

    @Test
    public void testUserList_byPuntosInteresRecorridos(){
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", "Pau");
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", "Pau");
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaC", "Pau");

        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", "Jana");

        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", "Anna");
        UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaC", "Anna");

        Usuario[] arr = {u1, u2, u4, u3};
        Assert.assertArrayEquals(UsuarioManagerImpl.getInstance().userList_byPuntosInteresRecorridos().toArray(), arr);
    }

    @After
    public void tearDown(){
        UsuarioManagerImpl.getInstance().cleanCache();
    }
}
