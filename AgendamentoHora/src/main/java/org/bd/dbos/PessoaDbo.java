package org.bd.dbos;

import java.util.Objects;

public  class PessoaDbo {
    private String nome;
    private int idade;

    public PessoaDbo(String nome, int idade) throws Exception {

        this.setNome(nome);
        this.setIdade(idade);
    }

    public void setNome(String nome) throws Exception{
        if (nome == null || nome.isEmpty())
            throw new Exception ("Nome não fornecido");
        this.nome = nome;
    }

    public void setIdade(int idade) throws Exception {
        if(idade < 5)
            throw new Exception("A idade inválida");
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome + '\'' +
                "Idade:" + idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaDbo pessoaDbo = (PessoaDbo) o;
        return idade == pessoaDbo.idade && Objects.equals(nome, pessoaDbo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade);
    }
}
