package org.example.vai_plmr_de_deus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.vai_plmr_de_deus.Estoque;
import org.example.vai_plmr_de_deus.Produto;

import java.io.IOException;

public class ControllerTelaVendedor {

    @FXML private Button Voltar;
    @FXML private ListView<Produto> lvProdutos;
    @FXML private Spinner<Integer> spQuantidade;
    @FXML private Label lblMensagem;

    private Produto produtoSelecionado;

    @FXML
    public void initialize() {

        lvProdutos.getItems().addAll(Estoque.produtos);

        spQuantidade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0)
        );

        lvProdutos.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> produtoSelecionado = newV
        );
    }

    public void atualizarEstoque() {

        if (produtoSelecionado == null) {
            mostrarMensagem("Selecione um produto!", "red");
            return;
        }

        produtoSelecionado.setQuantidade(spQuantidade.getValue());
        lvProdutos.refresh();

        mostrarMensagem("Estoque atualizado com sucesso!", "green");
    }

    public void solicitarReposicao() {

        if (produtoSelecionado == null) {
            mostrarMensagem("Selecione um produto!", "red");
            return;
        }

        mostrarMensagem(
                "Reposição solicitada para: " + produtoSelecionado.getNome(),
                "orange"
        );
    }

    private void mostrarMensagem(String texto, String cor) {
        lblMensagem.setText(texto);
        lblMensagem.setStyle("-fx-text-fill: " + cor + ";");
        lblMensagem.setVisible(true);
    }
    public void VoltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

