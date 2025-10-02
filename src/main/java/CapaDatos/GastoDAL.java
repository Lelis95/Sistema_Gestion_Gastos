
package CapaDatos;
import CapaEntidad.Gastos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author e-p-l
 */

public class GastoDAL {
    Connection cn = new ConexionBD().abrirConexion();
   public List listar() {
        List<Gastos> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_listarGastos()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Gastos(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBigDecimal(8)));
            }
        } catch (Exception e) {
            System.out.println("Error en  Listar: " + e.getMessage());
        }
        return lista;
    }
   public int agregar(Gastos g) {
       
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{ call sp_gastos_agregar(?,?,?,?,?,?,?) }");

            // Asignar valores a los parámetros
            cs.setInt(1, g.getIdProveedor());
            cs.setDate(2, new java.sql.Date(g.getFechaGasto().getTime())); // conversión necesaria
            cs.setString(3, g.getTipoDocumento());
            cs.setString(4, g.getNumeroDocumento());
            cs.setString(5, g.getConcepto());
            cs.setString(6, g.getMoneda());
            cs.setBigDecimal(7, g.getImporte());

            int f = cs.executeUpdate();
            if (f > 0) {
                r = 1;
            } else {
                r = 0;
            }

            cs.close();
        } catch (Exception ex) {
            r = 0;
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al insertar gasto: " + ex.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return r;
    }
    public int actualizar(Gastos g) {
    int r = 0; // r almacenará el número de filas afectadas
    try {
        // 1. Prepara la llamada al procedimiento
        CallableStatement cs = cn.prepareCall("{ call sp_actualizarGastos(?,?,?,?,?,?,?,?) }");
        cs.setInt(1, g.getIdgasto());
        cs.setInt(2, g.getIdProveedor());
        cs.setDate(3, new java.sql.Date(g.getFechaGasto().getTime()));
        cs.setString(4, g.getTipoDocumento());
        cs.setString(5, g.getNumeroDocumento());
        cs.setString(6, g.getConcepto());
        cs.setString(7, g.getMoneda());
        cs.setBigDecimal(8, g.getImporte());
        r = cs.executeUpdate(); 
      
        cs.close();
        } catch (Exception e) {
            System.out.println("Error en actualizar: " + e.getMessage());
        }
        return r; // Devuelve el número de filas actualizadas
}
    
    public List<Gastos> buscarPorConcepto(String concepto) {
        List<Gastos> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_buscarGastosPorConcepto(?)}");
            cs.setString(1, concepto);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Gastos(
                    rs.getInt("idGastos"),
                    rs.getInt("idProveedor"),
                    rs.getDate("fechaGasto"),
                    rs.getString("tipoDocumento"),
                    rs.getString("numeroDocumento"),
                    rs.getString("concepto"),
                    rs.getString("moneda"),
                    rs.getBigDecimal("importe")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar por concepto: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Gastos> buscarPorProveedor(int idProveedor) {
        List<Gastos> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_buscarGastoPorId(?)}");
            cs.setInt(1, idProveedor);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Gastos(
                    rs.getInt("idGastos"),
                    rs.getInt("idProveedor"),
                    rs.getDate("fechaGasto"),
                    rs.getString("tipoDocumento"),
                    rs.getString("numeroDocumento"),
                    rs.getString("concepto"),
                    rs.getString("moneda"),
                    rs.getBigDecimal("importe")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar por proveedor: " + e.getMessage());
        }
        return lista;
    }
    public int eliminar(int idGasto) {
    int r = 0;
    try {
        CallableStatement cs = cn.prepareCall("{ call sp_eliminarGastos(?) }");
        cs.setInt(1, idGasto);
        int f = cs.executeUpdate();
        if (f > 0) {
            r = 1;
        } else {
            r = 0;
        }
        cs.close();
    } catch (Exception ex) {
        r = 0;
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Error al eliminar gasto: " + ex.getMessage(),
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    return r;
}
}

