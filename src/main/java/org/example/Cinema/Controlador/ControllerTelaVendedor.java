package org.example.Cinema.Controlador;

import dao.ProdutoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Cinema.Model.Produto;

import java.io.IOException;

public class ControllerTelaVendedor {

    @FXML private Button Voltar;
    @FXML private ListView<Produto> lvProdutos;
    @FXML private Spinner<Integer> spQuantidade;
    @FXML private Label lblMensagem;

    private Produto produtoSelecionado;

    @FXML
    public void initialize() {

        ProdutoDao produtoDao = new ProdutoDao();
        lvProdutos.getItems().addAll(produtoDao.findAll());

        spQuantidade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0)
        );

        lvProdutos.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> produtoSelecionado = newV);
    }

    public void atualizarEstoque() {
        if (produtoSelecionado == null) {
            mostrarMensagem("Selecione um produto!", "red");
            return;
        }

        produtoSelecionado.setQuantidade(spQuantidade.getValue());

        ProdutoDao dao = new ProdutoDao();
        dao.update(produtoSelecionado);

        lvProdutos.refresh();
        mostrarMensagem("Estoque atualizado com sucesso!", "green");
    }

    public void solicitarReposicao() {
        if (produtoSelecionado == null) {
            mostrarMensagem("Selecione um produto!", "red");
            return;
        }

        java.sql.Connection conn = null;
        java.sql.PreparedStatement ps = null;
        try {
            conn = db.DB.getConnection();
            String sql = "INSERT INTO solicitacao_reposicao (produto_id, nome_produto, quantidade_solicitada) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, produtoSelecionado.getId());
            ps.setString(2, produtoSelecionado.getNome());
            ps.setInt(3, spQuantidade.getValue() > 0 ? spQuantidade.getValue() : 50);
            ps.executeUpdate();
            mostrarMensagem("Reposição solicitada para o gerente!", "green");
        } catch (java.sql.SQLException e) {
            mostrarMensagem("Erro ao enviar solicitação: " + e.getMessage(), "red");
        } finally {
            db.DB.closeStatment(ps);
        }
    }

    private void mostrarMensagem(String texto, String cor) {
        lblMensagem.setText(texto);
        lblMensagem.setStyle("-fx-text-fill: " + cor + ";");
        lblMensagem.setVisible(true);
    }
    public void VoltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

