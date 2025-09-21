/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Lelis Carlos
 */
public class ConexionBD {
    Connection cn = null;
    
    public Connection getConexion(){
        try{
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/regustrogastos?user=root&password=Lelis955&"
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
