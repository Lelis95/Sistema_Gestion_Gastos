
package CapaDatos;

import java.sql.*;
/**
 *
 * @author Lelis Carlos
 */
public class ConexionBD {
    Connection cn = null;
    
    public Connection abrirConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBGastos?user=root&password=12345&"
                    +"allowPublicKeyRetrieval=true&useSSL=false");
        }catch(Exception el){
            System.out.println("Error Sql :"+el.getMessage());
        }
        return cn;
    }
    
    public void cerrarConexion(Connection conn){
        if (conn != null) {
         try{
              cn.close();
            }catch(Exception el){
               System.out.println("Error al Cerrar Conexion : "+el.getMessage());
           }
      }
    }
}
