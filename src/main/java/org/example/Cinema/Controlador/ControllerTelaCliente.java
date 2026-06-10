package org.example.Cinema.Controlador;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Cinema.Model.CinemaData;
import org.example.Cinema.Model.Filme;

public class ControllerTelaCliente {

        @FXML private ListView<Filme> lvFilmes;
        @FXML private Label lblInfo;
        @FXML private ComboBox<String> cbSessoes;
        @FXML private Spinner<Integer> spQuantidade;
        @FXML private Label lblTotal;
        @FXML private Label lblMensagem;
        @FXML private Button Voltar;

        private Filme filmeSelecionado;

        @FXML
        public void initialize() {

            lvFilmes.getItems().addAll(CinemaData.filmes);

            spQuantidade.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1)
            );

            lvFilmes.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldV, newV) -> mostrarFilme(newV)
            );

            spQuantidade.valueProperty().addListener(
                    (obs, oldV, newV) -> calcularTotal()
            );
        }

        private void mostrarFilme(Filme filme) {
            if (filme == null) return;

            filmeSelecionado = filme;
            lblMensagem.setVisible(false);

            lblInfo.setText(
                    "Nome: " + filme.getNome() + "\n" +
                            "Diretor: " + filme.getDiretor() + "\n" +
                            "Classificação: " + filme.getClassificacao() + "\n" +
                            "Gênero: " + filme.getGenero() + "\n" +
                            "Duração: " + filme.getDuracao() + "\n" +
                            "Preço: R$ " + filme.getPreco()
            );

            cbSessoes.getItems().setAll(filme.getSessoes());
            cbSessoes.setValue(null);

            calcularTotal();
        }

        private void calcularTotal() {
            if (filmeSelecionado == null) return;
            double total = spQuantidade.getValue() * filmeSelecionado.getPreco();
            lblTotal.setText(String.format("Total: R$ %.2f", total));
        }

        public void comprar() {

            if (filmeSelecionado == null || cbSessoes.getValue() == null) {
                lblMensagem.setText("Selecione um filme e uma sessão!");
                lblMensagem.setStyle("-fx-text-fill: red;");
                lblMensagem.setVisible(true);
                return;
            }

            lblMensagem.setText("Compra concluída com sucesso!");
            lblMensagem.setStyle("-fx-text-fill: green;");
            lblMensagem.setVisible(true);
        }
    public void VoltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
