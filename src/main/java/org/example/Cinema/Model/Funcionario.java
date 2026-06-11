package org.example.Cinema.Model;

public class Funcionario {
    private int Id;
    private String Nome;
    private String Contato;
    private String Gênero;
    private String Cargo;
    private int Idade;
    private double Salario;

    public Funcionario(String nome, String contato, String genero, String cargo, int idade, double salario) {
        this.Nome = nome;
        this.Contato = contato;
        this.Gênero = genero;
        this.Cargo = cargo;
        this.Idade=idade;
        this.Salario=salario;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getContato() {
        return Contato;
    }
    public void setContato(String contato) {
        this.Contato = contato;
    }

    public String getGênero() {
        return Gênero;
    }
    public void setGênero(String gênero) {
        this.Gênero = gênero;
    }

    public String getCargo() {
        return Cargo;
    }
    public void setCargo(String cargo) {
        this.Cargo = Cargo;
    }

    public int getIdade() {
        return Idade;
    }
    public void setIdade(int Idade) {
        this.Idade = Idade;
    }

    public double getSalario() {
        return Salario;
    }
    public void setSalario(int salario) {
        this.Salario = salario;
    }

    @Override
    public String toString() {
        return Nome;
    }
}
