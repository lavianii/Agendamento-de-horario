package org.main;


import org.bd.daos.AgendaMarcadaDaos;
import org.bd.daos.AgendamentoDaos;
import org.bd.daos.PessoaDaos;
import org.bd.dbos.AgendaMarcadaDbos;
import org.bd.dbos.AgendamentoDbos;
import org.bd.dbos.PessoaDbo;

public class Main {

    public static void main(String[] args) throws Exception {
        String nome;
        int idade;
        int diaSemanaId;
        int pessoaId;
        int horarioDisponivelId;

        System.out.println("Bem vindo ao Agendamento de Horas");
        System.out.println("Escolha uma dessas opções:" +
                "\nDigite 1 - Cadastrar Cliente" +
                "\nDigite 2 - Agendar Horário" +
                "\nDigite 3 - Para Listar os Horarios Marcados");


        int opcao = Teclado.getUmInt();
        switch (opcao){
            case 1:
                System.out.println("Digite o nome do cliente:");
                nome = Teclado.getUmString();
                System.out.println("Digite a idade do cliente:");
                idade = Teclado.getUmInt();

                PessoaDaos.postPessoa(new PessoaDbo(nome, idade));
                break;
            case 2:
                var clientes = PessoaDaos.getAllPessoa();
                var agendamento = new AgendamentoDbos();
                System.out.println("Lista de Clientes");
                for (PessoaDbo cliente : clientes ){
                    System.out.println(cliente);
                }

                System.out.println("Digite o código do cliente:");
                pessoaId = Teclado.getUmInt();

                System.out.println("--------------------------");
                agendamento.listarDiaSemana();
                System.out.println("--------------------------");
                System.out.println("Digite o código do dia:");
                diaSemanaId = Teclado.getUmInt();

                System.out.println("--------------------------");
                agendamento.listarHora();
                System.out.println("--------------------------");
                System.out.println("Digite o horário:");
                horarioDisponivelId = Teclado.getUmInt();

                AgendamentoDaos.postAgendar(new AgendamentoDbos(horarioDisponivelId, diaSemanaId, pessoaId));
                var agendamentoId = AgendamentoDaos.getById(pessoaId);

                AgendaMarcadaDaos.postAgendaMarcada(new AgendaMarcadaDbos(pessoaId,agendamentoId));
                break;
            case 3:
                var listarAgenda = AgendaMarcadaDaos.getAll();
                System.out.println(listarAgenda);
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
        }
                
    }
}