package org.example.Cinema.Banco_De_Dados;

import java.util.List;

public class Filme {
     private String nome;
     private String diretor;
     private String classificacao;
     private String genero;
     private String duracao;
     private double preco;
     private List<String> sessoes;

     public Filme(String nome, String diretor, String classificacao, String genero, String duracao, double preco, List<String> sessoes) {
         this.nome = nome;
         this.diretor = diretor;
         this.classificacao = classificacao;
         this.genero = genero;
         this.duracao = duracao;
         this.preco = preco;
         this.sessoes = sessoes;
     }
        public String getNome() {
         return nome;
     }

     public void setNome(String nome) {
         this.nome = nome;
     }

     public String getDiretor() {
         return diretor;
     }

     public void setDiretor(String diretor) {
         this.diretor = diretor;
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

     public void setPreco(double preco) {
         this.preco = preco;
     }

     public List<String> getSessoes() {
         return sessoes;
     }

     public void setSessoes(List<String> sessoes) {
         this.sessoes = sessoes;
     }

     @Override
     public String toString() {
         return nome;
     }
}
