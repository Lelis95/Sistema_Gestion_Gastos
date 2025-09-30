/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UbigeoDAL {
    private ConexionBD conexionBD;

    public UbigeoDAL() {
        this.conexionBD = new ConexionBD();
    }

    public List<String> obtenerDepartamentos() {
        List<String> departamentos = new ArrayList<>();
        Connection conn = null;
        
        try {
            System.out.println("=== OBTENIENDO DEPARTAMENTOS ===");
            conn = conexionBD.abrirConexion();
            System.out.println("Conexión establecida: " + (conn != null));
            
            String sql = "SELECT departamento FROM ubdepartamento ORDER BY departamento";
            System.out.println("SQL: " + sql);
            
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                count++;
                String depto = rs.getString("departamento");
                System.out.println("Departamento " + count + ": " + depto);
                departamentos.add(depto);
            }
            
            System.out.println("Total departamentos encontrados: " + count);
            
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener departamentos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return departamentos;
    }

    public List<String> obtenerProvincias(String departamento) {
        List<String> provincias = new ArrayList<>();
        Connection conn = null;
        
        try {
            System.out.println("=== OBTENIENDO PROVINCIAS para: " + departamento + " ===");
            conn = conexionBD.abrirConexion();
            
            // Primero obtenemos el idDepa del departamento seleccionado
            String sqlIdDepa = "SELECT idDepa FROM ubdepartamento WHERE departamento = ?";
            PreparedStatement pstIdDepa = conn.prepareStatement(sqlIdDepa);
            pstIdDepa.setString(1, departamento);
            ResultSet rsIdDepa = pstIdDepa.executeQuery();
            
            int idDepa = 0;
            if (rsIdDepa.next()) {
                idDepa = rsIdDepa.getInt("idDepa");
                System.out.println("ID Departamento encontrado: " + idDepa);
            }
            rsIdDepa.close();
            pstIdDepa.close();
            
            // Ahora obtenemos las provincias de ese departamento
            String sql = "SELECT provincia FROM ubprovincia WHERE idDepa = ? ORDER BY provincia";
            System.out.println("SQL Provincias: " + sql + " con idDepa: " + idDepa);
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idDepa);
            ResultSet rs = pst.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                count++;
                String provincia = rs.getString("provincia");
                System.out.println("Provincia " + count + ": " + provincia);
                provincias.add(provincia);
            }
            
            System.out.println("Total provincias encontradas: " + count);
            
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener provincias: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return provincias;
    }

    public List<String> obtenerDistritos(String departamento, String provincia) {
        List<String> distritos = new ArrayList<>();
        Connection conn = null;
        
        try {
            System.out.println("=== OBTENIENDO DISTRITOS para: " + departamento + " - " + provincia + " ===");
            conn = conexionBD.abrirConexion();
            
            // Primero obtenemos el idDepa del departamento
            String sqlIdDepa = "SELECT idDepa FROM ubdepartamento WHERE departamento = ?";
            PreparedStatement pstIdDepa = conn.prepareStatement(sqlIdDepa);
            pstIdDepa.setString(1, departamento);
            ResultSet rsIdDepa = pstIdDepa.executeQuery();
            
            int idDepa = 0;
            if (rsIdDepa.next()) {
                idDepa = rsIdDepa.getInt("idDepa");
                System.out.println("ID Departamento: " + idDepa);
            }
            rsIdDepa.close();
            pstIdDepa.close();
            
            // Luego obtenemos el idProv de la provincia
            String sqlIdProv = "SELECT idProv FROM ubprovincia WHERE idDepa = ? AND provincia = ?";
            PreparedStatement pstIdProv = conn.prepareStatement(sqlIdProv);
            pstIdProv.setInt(1, idDepa);
            pstIdProv.setString(2, provincia.trim()); // Usamos trim() por si hay espacios
            ResultSet rsIdProv = pstIdProv.executeQuery();
            
            int idProv = 0;
            if (rsIdProv.next()) {
                idProv = rsIdProv.getInt("idProv");
                System.out.println("ID Provincia encontrado: " + idProv);
            }
            rsIdProv.close();
            pstIdProv.close();
            
            // Finalmente obtenemos los distritos de esa provincia
            String sql = "SELECT distrito FROM ubdistrito WHERE idProv = ? ORDER BY distrito";
            System.out.println("SQL Distritos: " + sql + " con idProv: " + idProv);
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idProv);
            ResultSet rs = pst.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                count++;
                String distrito = rs.getString("distrito");
                System.out.println("Distrito " + count + ": " + distrito);
                distritos.add(distrito);
            }
            
            System.out.println("Total distritos encontrados: " + count);
            
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener distritos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return distritos;
    }
}