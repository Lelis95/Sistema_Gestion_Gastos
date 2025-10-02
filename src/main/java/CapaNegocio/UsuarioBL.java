
package CapaNegocio;

import CapaDatos.UsuarioDAL;
import CapaEntidad.Usuario;
import java.util.List;

public class UsuarioBL {
    
    private UsuarioDAL oUsuarioDAL= new UsuarioDAL();
    
   // private UsuarioDAL dal;
    
    public List listar(){
        return oUsuarioDAL.listar();
    }
    public int agregarUsuario(Usuario ousuario){
        return oUsuarioDAL.agregar(ousuario);
    }
    public int actualizarUsuario(Usuario ousuario){
        return oUsuarioDAL.actualizar(ousuario);
    }
    public int eliminarUsuario(int id){
        return oUsuarioDAL.eliminar(id);
    }
    public Usuario listarUsuario(int id){
        return oUsuarioDAL.listar(id);
    } 
    public Usuario validarLogin(String login, String pass) {
    UsuarioDAL dal = new UsuarioDAL();
    return dal.login(login, pass);
}
    
    public String obtenerRolUsuario(String usuario) {
        return oUsuarioDAL.obtenerRolUsuario(usuario);
    }

    public int obtenerIdUsuario(String usuario) {
        return oUsuarioDAL.obtenerIdUsuario(usuario);
    }

    /*public String obtenerNombreUsuario(String usuario) {
        return oUsuarioDAL.obtenerNombreUsuario(usuario);
    }*/
}
