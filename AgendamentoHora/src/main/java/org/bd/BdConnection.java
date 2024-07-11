package org.bd;
import java.sql.*;

public class BdConnection {
    public Connection conectBd(){
        Connection connection = null;
         try {
             String url = "jdbc:mysql://127.0.0.2:3306/agendamentohora";
             connection = DriverManager.getConnection(url, "root", "1234");
             System.out.println("Conectado com sucesso ao BANCO DE DADOS.");
         } catch (SQLException sqlException){
             System.out.println("Houve um problema ao se conectar com o BANCO DE DADOS. " + sqlException.getMessage());
         }
        return connection;
    }
}
