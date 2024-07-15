package org.bd.dbos;

public class DiaSemanaDbo {
    private int id;
    private String nomeSemana;

    public DiaSemanaDbo() {}

    public DiaSemanaDbo(int id, String nomeSemana) {
        this.id = id;
        this.nomeSemana = nomeSemana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeSemana() {
        return nomeSemana;
    }

    public void setNomeSemana(String nomeSemana) {
        this.nomeSemana = nomeSemana;
    }

    @Override
    public String toString() {
        return "\n" + nomeSemana;
    }
}
