package org.bd.daos;

import org.bd.BdConnection;
import org.bd.dbos.AgendaMarcadaDbos;
import org.bd.dbos.AgendamentoDbos;
import org.bd.dbos.PessoaDbo;

import java.sql.*;
import java.util.ArrayList;

public class AgendaMarcadaDaos {
    static Connection connection;
    static PreparedStatement preparedStatement;

    public static void postAgendaMarcada(AgendaMarcadaDbos agendaMarcada) throws Exception
    {
        connection = new BdConnection().conectBd();

        if(agendaMarcada == null)
            throw new Exception("Os dados não foram fornecidos");

        try {
            String sql = "insert into agenda_marcada (pessoa_id, agendamento_id) " +
                    "values(?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, agendaMarcada.getPessoaId());
            preparedStatement.setInt(2, agendaMarcada.getAgendamentoId());
            preparedStatement.execute();
            preparedStatement.close();

            System.out.println("Agendado com sucesso!");

        } catch (Exception ex) {
            System.out.println("Houve um problema no agendamento");
            connection.close();
        }
    }
    public static ArrayList<Object> getAll() throws Exception {
        connection = new BdConnection().conectBd();
        var agendaMarcada = new ArrayList<Object>();

        try {
            String sql = "select  \n" +
                    "\tam.agenda_marcada_id,\n" +
                    "\tp.nome,\n" +
                    "\ta.ano_agendado, \n" +
                    "\tds.nome_semana \n" +
                    "from agenda_marcada am\n" +
                    "join pessoa p on am.pessoa_id = p.pessoa_id\n" +
                    "join agendamento a on am.agendamento_id = a.agendamento_id\n" +
                    "join dia_semana ds  on a.dia_semana_id = a.dia_semana_id;  \n";

            preparedStatement = connection.prepareStatement(sql);
            var st = connection.prepareStatement(sql);
            ResultSet result = st.executeQuery();

            while (result.next()){;
                var pessoa = new PessoaDbo();
                var agendamento = new AgendamentoDbos();

                pessoa.setNome(result.getNString("nome"));
                agendamento.setAnoAgendamento(result.getInt("ano_agendado"));
                agendamento.setDiaDaSemanaId(result.getInt("nome_semana"));

                agendaMarcada.add(pessoa);
                agendaMarcada.add(agendamento);
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
