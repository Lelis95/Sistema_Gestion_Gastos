
package CapaNegocio;

import CapaDatos.GastoDAL;
import java.util.List;
import CapaEntidad.Gastos;
/**
 *
 * @author e-p-l
 */
public class GastosBL {
    GastoDAL gastosDAL = new GastoDAL();
    public List listar(){
        return gastosDAL.listar();
    }
    public int agregar(Gastos g) {
        return gastosDAL.agregar(g); // Llama a DAL
    }
    public int actualizar(Gastos g) {
    return gastosDAL.actualizar(g);
    }
     public List<Gastos> buscarPorConcepto(String concepto) {
        return gastosDAL.buscarPorConcepto(concepto);
    }
    
    public List<Gastos> buscarPorProveedor(int idProveedor) {
        return gastosDAL.buscarPorProveedor(idProveedor);
    }
    public int eliminar(int idGasto) {
    return gastosDAL.eliminar(idGasto);
    }
}
