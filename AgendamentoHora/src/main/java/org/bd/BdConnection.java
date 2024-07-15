package org.bd;

import java.sql.*;

public class BdConnection {
    public Connection conectBd() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://127.0.0.2:3306/seu-bd";
            connection = DriverManager.getConnection(url, "seu-usuario", "sua-senha");

        } catch (SQLException sqlException) {
            System.out.println("Houve um problema ao se conectar com o BANCO DE DADOS. " + sqlException.getMessage());
        }
        return connection;
    }
}
