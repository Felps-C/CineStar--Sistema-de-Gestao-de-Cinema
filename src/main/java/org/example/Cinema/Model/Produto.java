package org.example.Cinema.Model;

public class Produto {

    private int Idlanche;
    private String nome;
    private int quantidade;
    private double valor;
    private String validade;

    public Produto(String nome, int quantidade, double valor, String validade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor= valor;
        this.validade= validade;

    }

    public int getId() { return Idlanche; }
    public void setId(int Id) {
        this.Idlanche = Id;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getValidade() { return validade; }
    public void setValidade(String validade) {
        this.validade = validade;
    }
    public double getValor() { return valor; }
    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome + " - Estoque: " + quantidade;
    }
}