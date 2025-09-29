
package CapaNegocio;

import CapaDatos.UsuarioDAL;
import CapaEntidad.Usuario;
import java.util.List;

public class UsuarioBL {
    
    UsuarioDAL oUsuarioDAL= new UsuarioDAL();
    
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
    public Usuario buscarUsuario(int id){
        return oUsuarioDAL.buscar(id);
    } 
}
