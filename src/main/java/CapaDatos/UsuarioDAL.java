//prueba de git hut
package CapaDatos;

import CapaEntidad.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAL {
    
    Connection cn = new ConexionBD().abrirConexion();
    
   public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_listarUsuario();}");
            ResultSet rs = cs.executeQuery();
           /* while (rs.next()) {
                lista.add(new Usuario(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getString(4),
                      rs.getString(5),  
                      rs.getString(6),
                      rs.getInt(7)
                ));
            }*/
        }
        catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return lista;
    }
   
   public int agregar(Usuario unUsuario) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InsertarProveedor(?,?,?,?,?,?,?,?,?)}");///////
            cs.setString(1, unUsuario.getNombreUsuario());
            cs.setString(2, unUsuario.getApellidoPaterno());
            cs.setString(3, unUsuario.getNombres());
            cs.setString(4, unUsuario.getContrasena());
            cs.setString(5, unUsuario.getPerfil());
            cs.setInt(6, unUsuario.getEstado());
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                r = rs.getInt("idUsuario");
            }
            cs.close();
        } catch (Exception ex) {
            System.out.println("Error en agregar: " + ex.getMessage());
        }
        return r;
    }
   
   
   public Usuario buscar(int codigo) {
    Usuario oUsuario = null;
    try {
        CallableStatement cs = cn.prepareCall("{call sp_ObtenerProveedor(?)}");
        cs.setInt(1, codigo);
        ResultSet rs = cs.executeQuery();

        if (rs.next()) {
            oUsuario = new Usuario();
            oUsuario.setIdUsuario(rs.getInt("idUsuario"));
            oUsuario.setNombreUsuario(rs.getString("nombreUsuario"));
            oUsuario.setApellidoPaterno(rs.getString("apellidoPaterno"));
            oUsuario.setNombres(rs.getString("nombres"));
            oUsuario.setContrasena(rs.getString("contrasena"));
            oUsuario.setPerfil(rs.getString("perfil"));
            oUsuario.setEstado(rs.getInt("estado"));
        }

        rs.close();
        cs.close();
    } catch (Exception ex) {
        System.out.println("Error en buscar: " + ex.getMessage());
    }
    return oUsuario;
   }
   
   
   public int actualizar(Usuario unUsuario) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActualizarProveedor(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, unUsuario.getIdUsuario());
            cs.setString(2, unUsuario.getNombreUsuario());
            cs.setString(3, unUsuario.getApellidoPaterno());
            cs.setString(4, unUsuario.getNombres());
            cs.setString(5, unUsuario.getContrasena());
            cs.setString(6, unUsuario.getPerfil());
            cs.setInt(7, unUsuario.getEstado());
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                r = rs.getInt("filas afectadas");
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error en actualizar: " + e.getMessage());
        }
        return r;
    }
   
   
   public int eliminar(int codigo) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_EliminarProveedor(?)}");
            cs.setInt(1, codigo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                r = rs.getInt("filas afectadas");
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error en eliminar: " + e.getMessage());
        }
        return r;
    }
}