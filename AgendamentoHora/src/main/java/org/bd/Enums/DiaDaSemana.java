package org.bd.Enums;

public enum DiaDaSemana {

    SEGUNDA_FEIRA(1, "Segunda-feira"),
    TERCA_FEIRA(2, "Terça-feira"),
    QUARTA_FEIRA(3, "Quarta-feira"),
    QUINTA_FEIRA(4, "Quinta-feira"),
    SEXTA_FEIRA(5, "Sexta-feira"),
    SABADO(6, "Sábado"),
    DOMINGO(7, "Domingo");

    private final int diaSemanaId;
    private final String nomeSemana;

    DiaDaSemana(int diaSemanaId, String nomeSemana) {
        this.diaSemanaId = diaSemanaId;
        this.nomeSemana = nomeSemana;
    }

    public int getDiaSemanaId() {
        return diaSemanaId;
    }

    public String getNomeSemana() {
        return nomeSemana;
    }
}