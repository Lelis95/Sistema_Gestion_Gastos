//prueba de git hut
package CapaDatos;

import CapaEntidad.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class UsuarioDAL {
    
    Connection cn = new ConexionBD().abrirConexion();
    
   public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_listarUsuario}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getString(4),
                      rs.getString(5),  
                      rs.getString(6),
                      rs.getString(7)
                ));
            }
        }
        catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return lista;
    }
   

   public int agregar(Usuario unUsuario) {
    int r = 0;
    CallableStatement cs = null;

    try {
        cs = cn.prepareCall("{call sp_insertarUsuario(?,?,?,?,?,?)}");
        cs.setString(1, unUsuario.getNombreUsuario());
        cs.setString(2, unUsuario.getApellidoPaterno());
        cs.setString(3, unUsuario.getNombres());
        cs.setString(4, unUsuario.getContrasena());
        cs.setString(5, unUsuario.getPerfil());
        cs.setString(6, unUsuario.getEstado());

        r = cs.executeUpdate();

    } catch (SQLException ex) {
        if (ex.getErrorCode() == 1062) {
            JOptionPane.showMessageDialog(null,
                    "El usuario '" + unUsuario.getNombreUsuario() + "' ya existe.",
                    "Error de duplicado",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al registrar usuario: " + ex.getMessage(),
                    "Error en BD",
                    JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        try {
            if (cs != null) cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return r;
}
   
   
   public Usuario listar(int codigo) {
    Usuario oUsuario = null;
    try {
        CallableStatement cs = cn.prepareCall("{call sp_buscarUsuario(?)}");
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
            oUsuario.setEstado(rs.getString("estado"));
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
            CallableStatement cs = cn.prepareCall("{call sp_actualizarUsuario(?,?,?,?,?,?,?)}");
            cs.setInt(1, unUsuario.getIdUsuario());
            cs.setString(2, unUsuario.getNombreUsuario());
            cs.setString(3, unUsuario.getApellidoPaterno());
            cs.setString(4, unUsuario.getNombres());
            cs.setString(5, unUsuario.getContrasena());
            cs.setString(6, unUsuario.getPerfil());
            cs.setString(7, unUsuario.getEstado());
            
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
            CallableStatement cs = cn.prepareCall("{call sp_eliminarUsuario(?)}");
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