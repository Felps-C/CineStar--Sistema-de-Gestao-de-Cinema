package org.example.Cinema.Model;

public class Clientes {
    private String nome;
    private int Id;

    public Clientes(String nome,String CPF) {
        this.nome = nome;
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return nome;
    }
}