package org.example.Cinema.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import dao.UsuarioDao;

public class ControllerCriarConta {

    @FXML
    private ListView<String> lvClientes;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblErro;

    public void initialize() {
        ObservableList<String> itens = FXCollections.observableArrayList(
                "Cliente",
                "Gerente",
                "Vendedor"
        );
        lvClientes.setItems(itens);
    }

    public void criarConta(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        String tipoSelecionado = lvClientes.getSelectionModel().getSelectedItem();

        if (email.isEmpty() || senha.isEmpty() || tipoSelecionado == null) {
            lblErro.setText("Preencha todos os campos e selecione seu cargo!");
            lblErro.setStyle("-fx-text-fill: red;");
            lblErro.setVisible(true);
            return;
        }

        UsuarioDao dao = new UsuarioDao();

        try {
            dao.cadastrar(email, senha, tipoSelecionado.toLowerCase());
            voltarLogin(event);

        } catch (Exception e) {
            e.printStackTrace();
            lblErro.setText("Erro ao salvar no banco de dados!");
            lblErro.setStyle("-fx-text-fill: red;");
            lblErro.setVisible(true);
        }
    }

    public void voltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}