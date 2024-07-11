package org.bd.daos;

import java.sql.*;
import org.bd.BdConnection;
import org.bd.dbos.PessoaDbo;

public class PessoaDaos {
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void cadastrarPessoa(PessoaDbo pessoa) throws Exception {
        connection = new BdConnection().conectBd();

        if(pessoa == null)
            throw new Exception("Os dados não foram fornecidos");

        try {
            String sql = "insert into pessoa (nome, idade) values (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setInt(2, pessoa.getIdade());
            preparedStatement.execute();
            System.out.println("Cadastrado com sucesso!");

        } catch (Exception ex){
            preparedStatement.close();
            throw new Exception("Hove um erro ao se cadastrar.");
        }

    }
}
