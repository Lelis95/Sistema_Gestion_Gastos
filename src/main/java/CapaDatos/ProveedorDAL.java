
package CapaDatos;

import CapaEntidad.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProveedorDAL {
       Connection cn = new ConexionBD().abrirConexion();

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ListarProveedores()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setRuc(rs.getString("ruc"));
                p.setRazonSocial(rs.getString("razonSocial"));
                p.setDireccion(rs.getString("direccion"));
                p.setDistrito(rs.getString("distrito"));
                p.setProvincia(rs.getString("provincia"));
                p.setDepartamento(rs.getString("departamento"));
                p.setTelefono(rs.getString("telefono"));
                p.setCelular(rs.getString("celular"));
                p.setCorreo(rs.getString("correo"));
                lista.add(p);
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error en listar: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Proveedor unProveedor) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InsertarProveedor(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, unProveedor.getRuc());
            cs.setString(2, unProveedor.getRazonSocial());
            cs.setString(3, unProveedor.getDireccion());
            cs.setString(4, unProveedor.getDistrito());
            cs.setString(5, unProveedor.getProvincia());
            cs.setString(6, unProveedor.getDepartamento());
            cs.setString(7, unProveedor.getTelefono());
            cs.setString(8, unProveedor.getCelular());
            cs.setString(9, unProveedor.getCorreo());
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                r = rs.getInt("idProveedor");
            }
            rs.close();
            cs.close();
        } catch (Exception ex) {
            System.out.println("Error en agregar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return r;
    }

    public Proveedor buscar(int codigo) {
        Proveedor oproveedor = null;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ObtenerProveedor(?)}");
            cs.setInt(1, codigo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                oproveedor = new Proveedor();
                oproveedor.setIdProveedor(rs.getInt("idProveedor"));
                oproveedor.setRuc(rs.getString("ruc"));
                oproveedor.setRazonSocial(rs.getString("razonSocial"));
                oproveedor.setDireccion(rs.getString("direccion"));
                oproveedor.setDistrito(rs.getString("distrito"));
                oproveedor.setProvincia(rs.getString("provincia"));
                oproveedor.setDepartamento(rs.getString("departamento"));
                oproveedor.setTelefono(rs.getString("telefono"));
                oproveedor.setCelular(rs.getString("celular"));
                oproveedor.setCorreo(rs.getString("correo"));
            }
            rs.close();
            cs.close();
        } catch (Exception ex) {
            System.out.println("Error en buscar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return oproveedor;
    }

    public Proveedor buscarPorRUC(String ruc) {
        Proveedor oproveedor = null;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_BuscarProveedorPorRUC(?)}");
            cs.setString(1, ruc);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                oproveedor = new Proveedor();
                oproveedor.setIdProveedor(rs.getInt("idProveedor"));
                oproveedor.setRuc(rs.getString("ruc"));
                oproveedor.setRazonSocial(rs.getString("razonSocial"));
                oproveedor.setDireccion(rs.getString("direccion"));
                oproveedor.setDistrito(rs.getString("distrito"));
                oproveedor.setProvincia(rs.getString("provincia"));
                oproveedor.setDepartamento(rs.getString("departamento"));
                oproveedor.setTelefono(rs.getString("telefono"));
                oproveedor.setCelular(rs.getString("celular"));
                oproveedor.setCorreo(rs.getString("correo"));
            }
            rs.close();
            cs.close();
        } catch (Exception ex) {
            System.out.println("Error en buscarPorRUC: " + ex.getMessage());
            ex.printStackTrace();
        }
        return oproveedor;
    }
    
    public int actualizar(Proveedor unProveedor) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActualizarProveedor(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, unProveedor.getIdProveedor());
            cs.setString(2, unProveedor.getRuc());
            cs.setString(3, unProveedor.getRazonSocial());
            cs.setString(4, unProveedor.getDireccion());
            cs.setString(5, unProveedor.getDistrito());
            cs.setString(6, unProveedor.getProvincia());
            cs.setString(7, unProveedor.getDepartamento());
            cs.setString(8, unProveedor.getTelefono());
            cs.setString(9, unProveedor.getCelular());
            cs.setString(10, unProveedor.getCorreo());
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                r = rs.getInt("filas_afectadas");
            }
            rs.close();
            cs.close();
        } catch (Exception e) {
            System.out.println("Error en actualizar: " + e.getMessage());
            e.printStackTrace();
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
                r = rs.getInt("filas_afectadas");
            }
            rs.close();
            cs.close();
        } catch (Exception e) {
            System.out.println("Error en eliminar: " + e.getMessage());
            e.printStackTrace();
        }
        return r;
    }
}