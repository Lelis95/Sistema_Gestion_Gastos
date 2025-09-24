
package CapaDatos;

import CapaEntidad.Gastos;
//import java.math.BigDecimal;
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
            CallableStatement cs = cn.prepareCall("{call sp_listarGasto()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Gastos(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBigDecimal(7)));
            }
        } catch (Exception e) {
            System.out.println("listarGastos: " + e.getMessage());
        }
        return lista;
    }
}
