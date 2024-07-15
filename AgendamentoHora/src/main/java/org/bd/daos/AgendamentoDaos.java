package org.bd.daos;

import java.sql.*;
import org.bd.BdConnection;
import org.bd.dbos.AgendaMarcadaDbos;
import org.bd.dbos.AgendamentoDbos;

public class AgendamentoDaos {
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void postAgendar(AgendamentoDbos agendamento) throws Exception {
        connection = new BdConnection().conectBd();

        if (agendamento == null)
            throw new Exception("Os dados não foram fornecidos");

        try {
            String checkSql = "SELECT agendamento_id FROM agenda_marcada WHERE pessoa_id = ?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setInt(1, agendamento.getPessoaId());
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                result.close();
                preparedStatement.close();
                throw new Exception("Já existe um agendamento para este usuário.");
            }
            result.close();
            preparedStatement.close();

            String sql = "INSERT INTO agendamento (ano_agendado, dia_semana_id, pessoa_id, horario_disponivel_id) " +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, agendamento.getAnoAgendamento());
            preparedStatement.setInt(2, agendamento.getDiaDaSemanaId());
            preparedStatement.setInt(3, agendamento.getPessoaId());
            preparedStatement.setInt(4, agendamento.getHorarioDisponivelId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int agendamentoId = generatedKeys.getInt(1);

                AgendaMarcadaDaos.postAgendaMarcada(new AgendaMarcadaDbos(agendamento.getPessoaId(), agendamentoId));
            }

            preparedStatement.close();

        } catch (Exception ex) {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            throw new Exception("Houve um erro ao agendar.", ex);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static int getById(int pessoaId) throws Exception {
        connection = new BdConnection().conectBd();
        int agendamento_id = 0;

        if (pessoaId <= 0)
            throw new Exception("Os dados não foram fornecidos");

        try {
            String sql = "SELECT agendamento_id FROM agendamento WHERE pessoa_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pessoaId);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                agendamento_id = result.getInt("agendamento_id");
            }

            result.close();
            preparedStatement.close();

        } catch (Exception ex) {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            throw new Exception("Houve um erro ao buscar o agendamento.", ex);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return agendamento_id;
    }
}
