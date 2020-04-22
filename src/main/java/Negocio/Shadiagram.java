/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dao.AmistadJpaController;
import Dao.Conexion;
import Dao.PublicacionJpaController;
import Dao.UsuarioJpaController;
import Dto.Amistad;
import Dto.Publicacion;
import Dto.Usuario;
import java.io.InputStream;
import java.sql.Blob;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Shadiagram {

    Conexion con;
    List<Usuario> lu;

    public Shadiagram() {
        con = Conexion.getConexion();
    }

    public void registrar(String nombre, String pss) {

        UsuarioJpaController uj = new UsuarioJpaController(con.getBd());
        uj.create(new Usuario(Integer.SIZE, nombre, "a@a.com", pss));
    }

    public Usuario buscar(String n, String p) {
        UsuarioJpaController uj = new UsuarioJpaController(con.getBd());
        lu = uj.findUsuarioEntities();

        for (Usuario u : lu) {
            if ((u.getUsuario().equals(n)) && (u.getPass().equals(p))) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscar2(String n) {
        UsuarioJpaController uj = new UsuarioJpaController(con.getBd());
        lu = uj.findUsuarioEntities();
        for (Usuario u : lu) {
            System.out.println("buscar2{ user: " + u.getNombre() + " n: " + n);

            if (u.getNombre().equalsIgnoreCase(n)) {
                return u;
            }
        }
        return null;
    }

    public void registraAmigo(String u, String n) {
        UsuarioJpaController uj = new UsuarioJpaController(con.getBd());
        lu = uj.findUsuarioEntities();

        for (Usuario user : lu) {
            System.out.println("user: " + user.getNombre() + " uhttp: " + u + " n: " + n);
            if (user.getUsuario().equals(u)) {
                AmistadJpaController adao = new AmistadJpaController(con.getBd());
                Amistad amigis = new Amistad(user, this.buscar2(n));
                adao.create(amigis);
                user.getAmistadList().add(amigis);
            }
        }
    }

    public List<Usuario> getUsuariosXamista(List<Amistad> l) {
        List<Usuario> lua = null;
        for (Amistad amistad : l) {
            lua.add(amistad.getAmistad());
        }
        return lua;
    }

    public List<Amistad> getseguidos(Usuario u) {
        return u.getAmistadList();
    }
    
    public void insertarImagen(String path,String u){
        PublicacionJpaController pgc= new PublicacionJpaController(con.getBd());
        Publicacion p = new Publicacion();
    }
}
