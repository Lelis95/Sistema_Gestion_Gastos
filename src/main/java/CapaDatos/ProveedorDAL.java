
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
        } catch (Exception e) {
            System.out.println("Error en listar: " + e.getMessage());
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
            cs.close();
        } catch (Exception ex) {
            System.out.println("Error en agregar: " + ex.getMessage());
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
            cs.close();
        } catch (Exception ex) {
            System.out.println("Error en buscar: " + ex.getMessage());
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
                r = rs.getInt("filas_afectadas");
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error en eliminar: " + e.getMessage());
        }
        return r;
    }
    /*Connection cn = new ConexionBD().abrirConexion();

    public List listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_listarProveedor()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("listarProducto: " + e.getMessage());
        }
        return lista;
    }

    public int agregar(Proveedor unProveedor) {
        int r = 0;
        try {
            CallableStatement cs = cn.prepareCall("{ call sp_insertarEmpresaTransporte (?,?) }");
            //Asignar valores a los parametros
            cs.setString(1, unProveedor.getRuc());
            cs.setString(2, unProveedor.getRazonSocial());
            cs.setString(3, unProveedor.getDireccion());
            cs.setString(4, unProveedor.getDistrito());
            cs.setString(5, unProveedor.getProvincia());
            cs.setString(6, unProveedor.getDepartamento());
            cs.setString(7, unProveedor.getTelefono());
            cs.setString(8, unProveedor.getCelular());
            cs.setString(9, unProveedor.getCorreo());
            int f = cs.executeUpdate();
            if (f > 0) {
                r = 1;
            } else {
                r = 0;
            }
            cs.close();
        } catch (Exception ex) {
            r = 0;
        }
        return r;
    }
    
    public Proveedor buscar (int codigo){
        Proveedor oproveedor=null;
        try{
            CallableStatement cs= cn.prepareCall("{call sp_buscarEmpresaTrasporte(?;?;?;?;?;?;?;?;?)}");
            cs.setInt(1, codigo);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                oproveedor=new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            }else{
                oproveedor=null;
            }
            cs.close();
            rs.close();
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        return oproveedor;
    }
    
    public int actualizar(Proveedor unProveedor){
        int r;
        try{
            CallableStatement cs=cn.prepareCall("{ call sp_actualizarEmpresaTransporte (?;?;?)}");
            cs.setInt(1,unProveedor.getIdProveedor());
            cs.setString(2, unProveedor.getRuc());
            cs.setString(3, unProveedor.getRazonSocial());
            cs.setString(4, unProveedor.getDireccion());
            cs.setString(5, unProveedor.getDistrito());
            cs.setString(6, unProveedor.getProvincia());
            cs.setString(7, unProveedor.getDepartamento());
            cs.setString(8, unProveedor.getTelefono());
            cs.setString(9, unProveedor.getCelular());
            cs.setString(10, unProveedor.getCorreo());
            int f=cs.executeUpdate();
            if (f>0){
                r=1;
            }
            else{
                r=0;
            }
            
            cs.close();
            
        }catch(Exception e){
            r=0;
        }
        return r;
    }
    
    public int eliminar(int codigo) {
    int r = 0;
    try {
        CallableStatement cs = cn.prepareCall("{ call sp_eliminarEmpresaTransporte (?) }");
        cs.setInt(1, codigo);
        int f = cs.executeUpdate();
        if (f > 0) {
            r = 1;
        } else {
            r = 0;
        }
        cs.close();
    } catch (Exception e) {
        r = 1;
    }
    return r;
}*/
}
