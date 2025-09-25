
package CapaNegocio;

import CapaDatos.UsuarioDAL;
import java.util.List;

public class UsuarioBL {
    
    UsuarioDAL oUsuario= new UsuarioDAL();
    
    public List listar(){
        return oUsuario.listar();
    }
    
}
