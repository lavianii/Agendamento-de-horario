package org.bd.daos;

import java.sql.*;
import org.bd.BdConnection;
import org.bd.dbos.AgendamentoDbos;

public class AgendamentoDaos {
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void postAgendar(AgendamentoDbos agendamento) throws Exception{
        connection = new BdConnection().conectBd();

        if(agendamento == null)
            throw new Exception("Os dados não foram fornecidos");

        try {
            String sql = "insert into agendamento (ano_agendado, dia_semana_id, pessoa_id, horario_disponivel_id) " +
                    "values (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, agendamento.getAnoAgendamento());
            preparedStatement.setInt(2, agendamento.getDiaDaSemanaId());
            preparedStatement.setInt(3, agendamento.getPessoaId());
            preparedStatement.setInt(4, agendamento.getHorarioDisponivelId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception ex){
            preparedStatement.close();
            throw new Exception("Hove um erro ao agendar.");
        }
    }

    public static int getById(int pessoaId) throws Exception{
        connection = new BdConnection().conectBd();
        int agendamento_id = 0;

        if(pessoaId <= 0)
            throw new Exception("Os dados não foram fornecidos");

        try {
            String sql = "SELECT agendamento_id FROM agendamento WHERE pessoa_id = " + pessoaId;
            var st = connection.prepareStatement(sql);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                agendamento_id = result.getInt("agendamento_id");
            }

            result.close();
            connection.close();
            preparedStatement.close();

        } catch (Exception ex){
            preparedStatement.close();
            throw new Exception("Hove um erro ao agendar.");
        }

        return agendamento_id;
    }
}
