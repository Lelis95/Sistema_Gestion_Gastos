
package CapaNegocio;

import CapaDatos.GastoDAL;
import java.util.List;
import CapaEntidad.Gastos;
/**
 *
 * @author e-p-l
 */
public class GastosBL {
    GastoDAL oGasto= new GastoDAL();
    public List listar(){
        return oGasto.listar();
    }
}
