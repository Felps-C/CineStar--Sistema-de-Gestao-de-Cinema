package org.example.Cinema.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Cinema.Banco_De_Dados.CinemaData;
import org.example.Cinema.Banco_De_Dados.Estoque;
import org.example.Cinema.Banco_De_Dados.Filme;
import org.example.Cinema.Banco_De_Dados.Produto;

import java.io.IOException;
import java.util.List;

public class ControllerTelaGerente {

    @FXML private Button Voltar;
    @FXML private ListView<Filme> lvFilmes;
    @FXML private TextField txtNome, txtDiretor, txtClassificacao, txtGenero, txtDuracao, txtPreco, txtSessoes;

    private Filme filmeSelecionado;

    @FXML private ListView<Produto> lvProdutos;
    @FXML private Spinner<Integer> spQuantidade;

    private Produto produtoSelecionado;

    @FXML
    public void initialize() {

        lvFilmes.getItems().addAll(CinemaData.filmes);
        lvFilmes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> carregarFilme(newV)
        );

        lvProdutos.getItems().addAll(Estoque.produtos);
        spQuantidade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0)
        );

        lvProdutos.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> produtoSelecionado = newV
        );
    }

    private void carregarFilme(Filme filme) {
        if (filme == null) return;

        filmeSelecionado = filme;

        txtNome.setText(filme.getNome());
        txtDiretor.setText(filme.getDiretor());
        txtClassificacao.setText(filme.getClassificacao());
        txtGenero.setText(filme.getGenero());
        txtDuracao.setText(filme.getDuracao());
        txtPreco.setText(String.valueOf(filme.getPreco()));
        txtSessoes.setText(String.join(",", filme.getSessoes()));
    }

    public void salvarFilme() {

        filmeSelecionado.setNome(txtNome.getText());
        filmeSelecionado.setDiretor(txtDiretor.getText());
        filmeSelecionado.setClassificacao(txtClassificacao.getText());
        filmeSelecionado.setGenero(txtGenero.getText());
        filmeSelecionado.setDuracao(txtDuracao.getText());
        filmeSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
        filmeSelecionado.setSessoes(List.of(txtSessoes.getText().split(",")));

        lvFilmes.refresh();
    }

    public void reporEstoque() {

        if (produtoSelecionado == null) return;

        produtoSelecionado.setQuantidade(spQuantidade.getValue());
        lvProdutos.refresh();
    }
    public void VoltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
