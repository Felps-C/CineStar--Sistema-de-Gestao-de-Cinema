package org.example.Cinema.Controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerLogin {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblErro;

    public void fazerLogin(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        Parent root = null;
        lblErro.setText("");

        if (email.equals("cliente") && senha.equals("123")) {
            root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/TelaCliente.fxml"));
        }

        else if (email.equals("vendedor") && senha.equals("123")) {
            root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/TelaVendedor.fxml"));
        }

        else if (email.equals("gerente") && senha.equals("123")) {
            root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/TelaGerente.fxml"));
        }

        else {
            lblErro.setText("Email ou senha inválidos");
            lblErro.setVisible(true);
            return;
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

