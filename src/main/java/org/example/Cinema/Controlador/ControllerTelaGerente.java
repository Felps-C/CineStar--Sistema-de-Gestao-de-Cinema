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

public class ControllerTelaGerente {

    @FXML private Button Voltar;
    @FXML private ListView<Filme> lvFilmes;
    @FXML private TextField txtNomeF, txtNomeP, txtClassificacao, txtGenero, txtDuracao, txtPreco, txtValidade, txtValor;
    @FXML private TextField txtQuantidade;
    @FXML private Label lblAvisoReposicao;

    private Filme filmeSelecionado;

    @FXML private ListView<Produto> lvProdutos;
    @FXML private Spinner<Integer> spQuantidade;

    private Produto produtoSelecionado;

    @FXML
    public void initialize() {

        FilmeDao dao = new FilmeDao();
        lvFilmes.getItems().addAll(dao.findAll());

        lvFilmes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> carregarFilme(newV));

        ProdutoDao produtoDao = new ProdutoDao();
        lvProdutos.getItems().addAll(produtoDao.findAll());

        spQuantidade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        lvProdutos.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> carregarEstoque(newV));
        verificarSolicitacoesReposicao();
    }

    private void carregarFilme(Filme filme) {
        if (filme == null) return;

        filmeSelecionado = filme;

        txtNomeF.setText(filme.getNome());
        txtClassificacao.setText(filme.getClassificacao());
        txtGenero.setText(filme.getGenero());
        txtDuracao.setText(filme.getDuracao());
        txtPreco.setText(String.valueOf(filme.getPreco()));
    }

    public void salvarFilme() {
        filmeSelecionado.setNome(txtNomeF.getText());
        filmeSelecionado.setClassificacao(txtClassificacao.getText());
        filmeSelecionado.setGenero(txtGenero.getText());
        filmeSelecionado.setDuracao(txtDuracao.getText());
        filmeSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));

        FilmeDao dao = new FilmeDao();
        dao.update(filmeSelecionado);

        lvFilmes.refresh();
    }

    private void carregarEstoque(Produto produto) {
        if (produto == null) return;

        produtoSelecionado = produto;

        txtNomeP.setText(produto.getNome());
        txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
        txtValor.setText(String.valueOf(produto.getValor()));
        txtValidade.setText(produto.getValidade());

        spQuantidade.getValueFactory().setValue(produto.getQuantidade());
    }

    public void reporEstoque() {
        if (produtoSelecionado == null) return;
        produtoSelecionado.setQuantidade(spQuantidade.getValue());
        ProdutoDao dao = new ProdutoDao();
        dao.update(produtoSelecionado);

        java.sql.Connection conn = null;
        java.sql.PreparedStatement ps = null;
        try {
            conn = db.DB.getConnection();
            String sql = "UPDATE solicitacao_reposicao SET status = 'CONCLUIDO' WHERE produto_id = ? AND status = 'PENDENTE'";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, produtoSelecionado.getId());
            ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao finalizar status da solicitação: " + e.getMessage());
        } finally {
            db.DB.closeStatment(ps);
        }
        verificarSolicitacoesReposicao();

        lvProdutos.refresh();
    }

    public void adicionarEstoque(ActionEvent event) throws IOException {
        Produto novo = new Produto(
                txtNomeP.getText(),
                Integer.parseInt(txtQuantidade.getText()),
                Double.parseDouble(txtValor.getText()),
                txtValidade.getText()
        );

        ProdutoDao dao = new ProdutoDao();
        dao.insert(novo);

        lvProdutos.getItems().add(novo);
        lvProdutos.refresh();
    }

    public void removerEstoque(ActionEvent event) throws IOException {
        Produto selecionado = lvProdutos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            ProdutoDao dao = new ProdutoDao();
            dao.deleteById(selecionado.getId());
            lvProdutos.getItems().remove(selecionado);
            lvProdutos.refresh();
        } else {
            System.out.println("Nenhum produto selecionado para remover!");
        }
    }
    private void verificarSolicitacoesReposicao() {
        java.sql.Connection conn = null;
        java.sql.PreparedStatement st = null;
        java.sql.ResultSet rs = null;
        try {
            conn = db.DB.getConnection();
            String sql = "SELECT nome_produto, quantidade_solicitada FROM solicitacao_reposicao WHERE status = 'PENDENTE' ORDER BY Idsolicitacao DESC LIMIT 1";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                String nomeProd = rs.getString("nome_produto");
                int qtd = rs.getInt("quantidade_solicitada");
                lblAvisoReposicao.setText("⚠️ Vendedor solicitou reposição de: " + nomeProd + " (Qtd: " + qtd + ")");
                lblAvisoReposicao.setVisible(true);
            } else {
                lblAvisoReposicao.setVisible(false);
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao verificar notificações: " + e.getMessage());
        } finally {
            db.DB.closeResultSet(rs);
            db.DB.closeStatment(st);
        }
    }

    public void adicionarFilme() {
        Filme novo = new Filme(
                txtNomeF.getText(),
                txtClassificacao.getText(),
                txtGenero.getText(),
                txtDuracao.getText(),
                Double.parseDouble(txtPreco.getText())
        );
        FilmeDao dao = new FilmeDao();
        dao.insert(novo);
        lvFilmes.getItems().add(novo);
        lvFilmes.refresh();
    }

    public void removerFilme(ActionEvent event) throws IOException {
        Filme selecionado = lvFilmes.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            FilmeDao dao = new FilmeDao();
            dao.deleteById(selecionado.getId());
            lvFilmes.getItems().remove(selecionado);
            lvFilmes.refresh();
        } else {
            System.out.println("Nenhum filme selecionado para remover!");
        }
    }

    public void VoltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}