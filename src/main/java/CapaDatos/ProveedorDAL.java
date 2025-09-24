/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import CapaEntidad.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lelis Carlos
 */
public class ProveedorDAL {
    Connection cn = new ConexionBD().abrirConexion();

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
}
}
