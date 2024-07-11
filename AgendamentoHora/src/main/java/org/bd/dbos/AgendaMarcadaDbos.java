package org.bd.dbos;

import java.util.Objects;

public class AgendaMarcadaDbos {
    private int agendaMarcadaId;
    private int pessoaId;
    private int agendamentoId;

    public AgendaMarcadaDbos(){}

    public AgendaMarcadaDbos(int pessoaId, int agendaMarcadaId) {

        this.pessoaId = pessoaId;
        this.agendaMarcadaId = agendaMarcadaId;
    }

    public int getAgendaMarcadaId() {
        return agendaMarcadaId;
    }

    public void setAgendaMarcadaId(int agendaMarcadaId) {
        this.agendaMarcadaId = agendaMarcadaId;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(int agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    @Override
    public String toString() {
        return "AgendaMarcada{" +
                "agendaMarcadaId=" + agendaMarcadaId +
                ", pessoaId=" + pessoaId +
                ", agendamentoId=" + agendamentoId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgendaMarcadaDbos that = (AgendaMarcadaDbos) o;
        return agendaMarcadaId == that.agendaMarcadaId && pessoaId == that.pessoaId && agendamentoId == that.agendamentoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agendaMarcadaId, pessoaId, agendamentoId);
    }
}
