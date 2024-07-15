package org.main;

import org.bd.daos.AgendaMarcadaDaos;
import org.bd.daos.AgendamentoDaos;
import org.bd.daos.PessoaDaos;
import org.bd.dbos.AgendamentoDbos;
import org.bd.dbos.PessoaDbo;


public class Menu {

    public void exibirMenu() throws Exception {
        System.out.println("Bem vindo ao Agendamento de Horas");
        System.out.println("Escolha uma dessas opções:" +
                "\nDigite 1 - Cadastrar Cliente" +
                "\nDigite 2 - Agendar Horário" +
                "\nDigite 3 - Para Listar os Horarios Marcados");

        int opcao = Teclado.getUmInt();
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                agendarHorario();
                break;
            case 3:
                listarHorariosMarcados();
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
        }
    }

    private void cadastrarCliente() throws Exception {
        System.out.println("Digite o nome do cliente:");
        String nome = Teclado.getUmString();
        System.out.println("Digite a idade do cliente:");
        int idade = Teclado.getUmInt();

        PessoaDaos.postPessoa(new PessoaDbo(nome, idade));
    }

    private void agendarHorario() throws Exception {
        var clientes = PessoaDaos.getAllPessoa();
        var agendamento = new AgendamentoDbos();
        System.out.println("Lista de Clientes");
        for (PessoaDbo cliente : clientes) {
            System.out.println(cliente);
        }

        System.out.println("Digite o código do cliente:");
        int pessoaId = Teclado.getUmInt();

        System.out.println("--------------------------");
        agendamento.listarDiaSemana();
        System.out.println("--------------------------");
        System.out.println("Digite o código do dia:");
        int diaSemanaId = Teclado.getUmInt();

        System.out.println("--------------------------");
        agendamento.listarHora();
        System.out.println("--------------------------");
        System.out.println("Digite o horário:");
        int horarioDisponivelId = Teclado.getUmInt();

        AgendamentoDaos.postAgendar(new AgendamentoDbos(horarioDisponivelId, diaSemanaId, pessoaId));
    }

    private void listarHorariosMarcados() throws Exception {
        var listarAgenda = AgendaMarcadaDaos.getAll();
        System.out.println(listarAgenda);
    }
}
