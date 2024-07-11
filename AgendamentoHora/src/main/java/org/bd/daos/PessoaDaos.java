package org.bd.daos;

import java.sql.*;
import java.util.ArrayList;

import org.bd.BdConnection;
import org.bd.dbos.PessoaDbo;

public class PessoaDaos {
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void postPessoa(PessoaDbo pessoa) throws Exception
    {
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
            preparedStatement.close();

        } catch (Exception ex){
            preparedStatement.close();
            throw new Exception("Hove um erro ao se cadastrar.");
        }
    }

    public static ArrayList<PessoaDbo> getAllPessoa() throws Exception
    {
        connection = new BdConnection().conectBd();
        var pessoas = new ArrayList<PessoaDbo>();

        try {
            String sql = "SELECT * FROM pessoa";

            preparedStatement = connection.prepareStatement(sql);
            var st = connection.prepareStatement(sql);
            ResultSet result = st.executeQuery();

            while (result.next()){;
                var pessoa = new PessoaDbo();
                pessoa.setPessoaId(result.getInt("pessoa_id"));
                pessoa.setNome(result.getNString("nome"));
                pessoa.setIdade(result.getInt("idade"));

                pessoas.add(pessoa);
            }
            result.close();
            connection.close();
            preparedStatement.close();
        } catch (Exception ex){
            throw new Exception("Erro ao procurar a pessoa");
        }

        return pessoas;
    }


}
