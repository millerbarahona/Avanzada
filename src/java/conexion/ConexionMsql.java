package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMsql {

    public static Connection cnx = null;
    public static ConexionMsql instance;

    private ConexionMsql() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_pedidos","root","");
        } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Erro conectar bd bd: "+ex.getMessage());
            }
    }
    public static synchronized ConexionMsql getInstance(){
        if(instance==null){
            instance=new ConexionMsql();
        }
        return instance;
    }
    
    public Connection getcnn(){
        return cnx;
    }
    public void cerrarConexion(){
        instance=null;
    }
}