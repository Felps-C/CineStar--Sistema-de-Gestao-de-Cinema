package org.example.Cinema.Controlador;

import dao.FilmeDao;
import dao.ProdutoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Cinema.Model.Filme;
import org.example.Cinema.Model.Produto;

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

        FilmeDao dao = new FilmeDao();
        lvFilmes.getItems().addAll(dao.findAll());

        lvFilmes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> carregarFilme(newV)
        );

        ProdutoDao produtoDao = new ProdutoDao();
        lvProdutos.getItems().addAll(produtoDao.findAll());
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
        txtClassificacao.setText(filme.getClassificacao());
        txtGenero.setText(filme.getGenero());
        txtDuracao.setText(filme.getDuracao());
        txtPreco.setText(String.valueOf(filme.getPreco()));
    }

    public void salvarFilme() {
        filmeSelecionado.setNome(txtNome.getText());
        filmeSelecionado.setClassificacao(txtClassificacao.getText());
        filmeSelecionado.setGenero(txtGenero.getText());
        filmeSelecionado.setDuracao(txtDuracao.getText());
        filmeSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));

        // Atualiza no banco
        FilmeDao dao = new FilmeDao();
        dao.update(filmeSelecionado);

        lvFilmes.refresh();
    }

    public void reporEstoque() {
        if (produtoSelecionado == null) return;

        produtoSelecionado.setQuantidade(spQuantidade.getValue());

        // Atualiza no banco
        ProdutoDao dao = new ProdutoDao();
        dao.update(produtoSelecionado);

        lvProdutos.refresh();
    }

    public void VoltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
