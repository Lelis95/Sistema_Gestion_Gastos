
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Lelis Carlos
 */
public class ConexionBD {
    Connection cn = null;
    
    public Connection abrirConexion(){
        try{
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/registrogastos?user=root&password=Lelis955&"
                    +"allowPublicKeyRetrieval=true&useSSL=false");
        }catch(Exception el){
            System.out.println("Error Sql :"+el.getMessage());
        }
        return cn;
    }
    
    public void cerrarConexion(){
        try{
            cn.close();
        }catch(Exception el){
            System.out.println("Error al Cerrar Conexion : "+el.getMessage());
        }
    }
}
