/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dao.Conexion;
import Dao.UsuarioJpaController;
import Dto.Amistad;
import Dto.Usuario;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Shadiagram {

    Conexion con; 
    List<Usuario> lu;
    
    public Shadiagram() {
        con=Conexion.getConexion();
    }
    
    
    public void registrar(String nombre,String pss){
        
        UsuarioJpaController uj=new UsuarioJpaController(con.getBd());
        uj.create(new Usuario(Integer.SIZE, nombre, "a@a.com", pss));
    }
    
    public Usuario buscar(String n,String p){
        UsuarioJpaController uj=new UsuarioJpaController(con.getBd());
        lu=uj.findUsuarioEntities();
        
        for(Usuario u : lu){
            System.out.println(u.getUsuario()+" , "+u.getPass());
            System.out.println(n+" , "+ p);
            if((u.getUsuario()==n) && (u.getPass() == p)){
                return u;
            }
        }
       return null;
    }
    public Usuario buscar2(String n){
        UsuarioJpaController uj=new UsuarioJpaController(con.getBd());
        lu=uj.findUsuarioEntities();
        
        for(Usuario u : lu){
            if(u.getNombre()==n){
                return u;
            }
        }
       return null;
    }
    public void registraAmigo(String u,String n){
        UsuarioJpaController uj=new UsuarioJpaController(con.getBd());
        lu=uj.findUsuarioEntities();
        
        for(Usuario user : lu){
            if(user.getNombre()== u ){
                user.getAmistadList().add(new Amistad(user,this.buscar2(n)));
            }
        }
    }
    public List<Usuario> getUsuariosXamista(List<Amistad> l){
        List<Usuario> lua=null;
        for (Amistad amistad : l) {
            lua.add(amistad.getAmistad());
        }
        return lua;
    }
    public List<Amistad> getseguidos(Usuario u){
        return u.getAmistadList();
    }
}
