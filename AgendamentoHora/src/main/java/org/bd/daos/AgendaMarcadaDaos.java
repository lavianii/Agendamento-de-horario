package org.bd.daos;

import org.bd.BdConnection;
import org.bd.dbos.AgendaMarcadaDbos;
import org.bd.dbos.AgendamentoDbos;
import org.bd.dbos.DiaSemanaDbo;
import org.bd.dbos.PessoaDbo;

import java.sql.*;
import java.util.ArrayList;

public class AgendaMarcadaDaos {
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void postAgendaMarcada(AgendaMarcadaDbos agendaMarcada) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (agendaMarcada == null) {
                throw new IllegalArgumentException("Os dados não foram fornecidos");
            }

            if (!verificaExistenciaAgendamento(agendaMarcada.getAgendamentoId())) {
                throw new IllegalArgumentException("O agendamento_id especificado não existe na tabela agendamento");
            }

            connection = new BdConnection().conectBd();

            String sql = "INSERT INTO agenda_marcada (pessoa_id, agendamento_id) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, agendaMarcada.getPessoaId());
            preparedStatement.setInt(2, agendaMarcada.getAgendamentoId());
            preparedStatement.execute();

            System.out.println("Agendado com sucesso!");

        } catch (Exception ex) {
            System.out.println("Houve um problema no agendamento: " + ex.getMessage());
            throw ex; // Lança a exceção para ser tratada no código cliente, se necessário
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public static boolean verificaExistenciaAgendamento(int agendamentoId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean existe = false;

        try {
            connection = new BdConnection().conectBd();
            String sql = "SELECT * FROM agendamento WHERE agendamento_id= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, agendamentoId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            throw new SQLException("Erro ao verificar existência do agendamento", ex);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return existe;
    }

    public static ArrayList<Object> getAll() throws Exception {
        connection = new BdConnection().conectBd();
        var agendaMarcada = new ArrayList<Object>();

        try {
            String sql = "SELECT " +
                    "    am.agenda_marcada_id, " +
                    "    p.nome, " +
                    "    a.ano_agendado, " +
                    "    ds.nome_semana " +
                    "FROM agenda_marcada am " +
                    "JOIN pessoa p ON am.pessoa_id = p.pessoa_id " +
                    "JOIN agendamento a ON am.agendamento_id = a.agendamento_id " +
                    "JOIN dia_semana ds ON a.dia_semana_id = ds.dia_semana_id;";

            preparedStatement = connection.prepareStatement(sql);
            var st = connection.prepareStatement(sql);
            ResultSet result = st.executeQuery();

            while (result.next()){;
                var pessoa = new PessoaDbo();
                var agendamento = new AgendamentoDbos();
                var diaSemana = new DiaSemanaDbo();

                pessoa.setNome(result.getNString("nome"));
                agendamento.setAnoAgendamento(result.getInt("ano_agendado"));
                diaSemana.setNomeSemana(result.getString("nome_semana"));

                agendaMarcada.add(pessoa);
                agendaMarcada.add(agendamento);
                agendaMarcada.add(diaSemana);
            }
            result.close();
            connection.close();
            preparedStatement.close();
        } catch (Exception ex){
            throw new Exception("Erro ao procurar a pessoa");
        }

        return agendaMarcada;
    }

}
