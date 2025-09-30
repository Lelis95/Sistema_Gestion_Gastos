/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package CapaNegocio;

import CapaDatos.ProveedorDAL;
import CapaEntidad.Proveedor;
import java.util.List;

/**
 *
 * @author Lelis Carlos
 */
public class ProveedorBL {
    ProveedorDAL oProveedorDAL = new ProveedorDAL();
    
    public List listarProveedor(){
        return oProveedorDAL.listar();
    }
    public int agregarProveedor(Proveedor oproveedor){
        return oProveedorDAL.agregar(oproveedor);
    }
    public int actualizarProveedor(Proveedor oproveedor){
        return oProveedorDAL.actualizar(oproveedor);
    }
    public int eliminarProveedor(int id){
        return oProveedorDAL.eliminar(id);
    }
    public Proveedor buscarProveedor(int id){
        return oProveedorDAL.buscar(id);
    }
    public Proveedor buscarProveedorPorRUC(String ruc){
        return oProveedorDAL.buscarPorRUC(ruc);
    }
}
