package org.bd.dbos;

import org.bd.Enums.DiaDaSemana;
import org.bd.Enums.HorarioDisponivel;

import java.time.LocalDate;
import java.util.Objects;

public class AgendamentoDbos {
    private int agendamentoId;
    private int anoAgendamento;
    private int diaDaSemanaId;
    private int pessoaId;
    private int horarioDisponivelId;

    public AgendamentoDbos() {
    }

    public AgendamentoDbos(int horarioDisponivelId, int diaDaSemanaId,
            int pessoaId) throws Exception {
        this.anoAgendamento = LocalDate.now().getYear();
        this.setHorarioDisponivelId(horarioDisponivelId);
        this.setDiaDaSemanaId(diaDaSemanaId);
        this.pessoaId = pessoaId;
    }

    public void listarHora() {
        for (HorarioDisponivel horario : HorarioDisponivel.values()) {
            System.out.println(horario.getNumero() + " - " + horario.getHora());
        }
    }

    public void listarDiaSemana() {
        for (DiaDaSemana diaDaSemana : DiaDaSemana.values()) {
            System.out.println(diaDaSemana.getDiaSemanaId() + " - " + diaDaSemana.getNomeSemana());
        }
    }

    public void setHorarioDisponivelId(int horarioDisponivelId) throws Exception {
        if (horarioDisponivelId <= 0 || horarioDisponivelId >= 21)
            throw new Exception("Código de horário inválido");

        this.horarioDisponivelId = horarioDisponivelId;
    }

    public void setDiaDaSemanaId(int diaDaSemanaId) throws Exception {
        if (diaDaSemanaId <= 0 || diaDaSemanaId >= 8)
            throw new Exception("Código de dia da semana inválido");

        this.diaDaSemanaId = diaDaSemanaId;
    }

    public void setAnoAgendamento(int anoAgendamento) {
        this.anoAgendamento = anoAgendamento;
    }

    public int getAgendamentoId() {
        return agendamentoId;
    }

    public int getAnoAgendamento() {
        return anoAgendamento;
    }

    public int getDiaDaSemanaId() {
        return diaDaSemanaId;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public int getHorarioDisponivelId() {
        return horarioDisponivelId;
    }

    @Override
    public String toString() {
        return "\nAno: " + getAnoAgendamento();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AgendamentoDbos that = (AgendamentoDbos) o;
        return agendamentoId == that.agendamentoId && anoAgendamento == that.anoAgendamento && pessoaId == that.pessoaId
                && diaDaSemanaId == that.diaDaSemanaId && horarioDisponivelId == that.horarioDisponivelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agendamentoId, anoAgendamento, diaDaSemanaId, pessoaId, horarioDisponivelId);
    }
}
