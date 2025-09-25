//prueba de git hut
package CapaDatos;

import CapaEntidad.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAL {
    
    Connection cn = new ConexionBD().abrirConexion();
    
   public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_listarUsuario();}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getString(4),
                      rs.getString(5),  
                      rs.getString(6),
                      rs.getInt(7)
                ));
            }
        }
        catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return lista;
    }
    
    /*public int agregar(Usuario usu) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("");
            cs.setString(1, usu.getNombreUsuario());
            cs.setString(2, usu.getApellidoMaterno());
        }
    }*/
}
