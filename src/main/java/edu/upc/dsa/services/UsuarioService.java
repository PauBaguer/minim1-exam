package edu.upc.dsa.services;

import edu.upc.dsa.TO.PasarPuntoInteresTO;
import edu.upc.dsa.TO.PuntosInteresTO;
import edu.upc.dsa.TO.UsuarioBuilder;
import edu.upc.dsa.TO.UsuarioInfoTO;
import edu.upc.dsa.UsuarioManagerImpl;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/usuarios")
@Path("/usuarios")
public class UsuarioService {

    public UsuarioService() {
        if(UsuarioManagerImpl.getInstance().getUsuarioHashMap().size() == 0) {


            Usuario u1 = new Usuario("Pau", "Baguer", "a@a.a");
            Usuario u2 = new Usuario("Anna", "Granes", "b@b.b");
            Usuario u3 = new Usuario("Maria", "Baguer", "c@c.c");
            Usuario u4 = new Usuario("Jana", "Baguer", "d@d.d");


            UsuarioManagerImpl.getInstance().addUser(u1);
            UsuarioManagerImpl.getInstance().addUser(u2);
            UsuarioManagerImpl.getInstance().addUser(u3);
            UsuarioManagerImpl.getInstance().addUser(u4);

            UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", "Pau");
            UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", "Pau");
            UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaC", "Pau");

            UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaA", "Jana");

            UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaB", "Anna");
            UsuarioManagerImpl.getInstance().pasarPuntoInteres("CasillaC", "Anna");
        }
    }

    @POST
    @ApiOperation(value = "Añadir usuario", notes = "Añadir usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= UsuarioBuilder.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(UsuarioBuilder u) {

        if (u.getName()==null)  return Response.status(500).entity(u).build();
        UsuarioManagerImpl.getInstance().addUser(u.buildUsuario());
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get usuarios ordenados alfabeticamente", notes = "get usuarios ordenados alfabeticamente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    //No uso TO porque va a ser identico al original, y no hay ciclos
    public Response getUsuarios(){
        List<Usuario> usuarios = new LinkedList<>(UsuarioManagerImpl.getInstance().userList_byName());

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get informacion de un usuario", notes = "get informacion de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UsuarioInfoTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/info/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam("nombre") String nombre){
        UsuarioInfoTO u = UsuarioManagerImpl.getInstance().userInfo(nombre);
        if(u==null) return Response.status(404).build();

        GenericEntity<UsuarioInfoTO> entity = new GenericEntity<UsuarioInfoTO>(u){};
        return Response.status(201).entity(entity).build();

    }

    @PUT
    @ApiOperation(value = "Pasar por punto interes", notes = "Pasar por punto interes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= PasarPuntoInteresTO.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/pasarPuntoInteres")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pasarPuntoInteres(PasarPuntoInteresTO u) {

        if (u.getNombre()==null || u.getPuntoInteres() == null)  return Response.status(500).entity(u).build();
        UsuarioManagerImpl.getInstance().pasarPuntoInteres(u.getPuntoInteres(), u.getNombre());
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get puntos de interes de un usuario", notes = "get puntos de interes de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntosInteresTO.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/puntosInteres/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosInteresUsuario(@PathParam("nombre") String nombre){
        Usuario u = UsuarioManagerImpl.getInstance().getUsuarioHashMap().get(nombre);
        if(u==null) return Response.status(404).build();
        PuntosInteresTO p = new PuntosInteresTO(UsuarioManagerImpl.getInstance().puntosInteresUsuario(nombre));

        GenericEntity<PuntosInteresTO> entity = new GenericEntity<PuntosInteresTO>(p){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get usuarios que han pasado por un punto de interes", notes = "get usuarios que han pasado por un punto de interes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/usuariosEnPuntoInteres/{punto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosEnPuntoInteres(@PathParam("punto") String punto){
        if(punto==null) return Response.status(404).build();
        List<Usuario> p = UsuarioManagerImpl.getInstance().usuariosEnPuntoInteres(punto);

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(p){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get usuarios ordenados descendientemente por puntos de interes recorridos", notes = "get usuarios ordenados descendientemente por puntos de interes recorridos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/usuarioPorPuntosRecorridos")
    @Produces(MediaType.APPLICATION_JSON)
    //No uso TO porque va a ser identico al original, y no hay ciclos
    public Response getUsuariosByPuntosInteresRecorridos(){
        List<Usuario> usuarios = new LinkedList<>(UsuarioManagerImpl.getInstance().userList_byPuntosInteresRecorridos());

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios){};
        return Response.status(201).entity(entity).build();

    }



}
