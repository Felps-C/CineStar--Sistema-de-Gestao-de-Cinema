package org.example.Cinema.Model;

import java.util.List;

public class Filme {
     private int Id;
     private String nome;
     private String classificacao;
     private String genero;
     private String duracao;
     private double preco;

     public Filme(String nome, String classificacao, String genero, String duracao, double preco) {
         this.nome = nome;
         this.classificacao = classificacao;
         this.genero = genero;
         this.duracao = duracao;
         this.preco = preco;
     }


    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
         return nome;
     }
     public void setNome(String nome) {
         this.nome = nome;
     }

     public String getClassificacao() {
         return classificacao;
     }
     public void setClassificacao(String classificacao) {
         this.classificacao = classificacao;
     }

     public String getGenero() {
         return genero;
     }
     public void setGenero(String genero) {
         this.genero = genero;
     }

     public String getDuracao() {
         return duracao;
     }
     public void setDuracao(String duracao) {
         this.duracao = duracao;
     }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {this.preco = preco;}

     @Override
     public String toString() {
         return nome;
     }
}
