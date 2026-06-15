package org.example.Cinema.Controlador;

import dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Cinema.Model.Usuario;

import java.io.IOException;

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

        UsuarioDao dao = new UsuarioDao();

        try {
            Usuario usuarioLogado = dao.autenticar(email, senha);

            if (usuarioLogado != null) {
                String tipo = usuarioLogado.getTipo().toLowerCase();

                if (tipo.equals("cliente")) {
                    root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/TelaCliente.fxml"));
                }
                else if (tipo.equals("vendedor")) {
                    root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/TelaVendedor.fxml"));
                }
                else if (tipo.equals("gerente")) {
                    root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/TelaGerente.fxml"));
                }
                else {
                    lblErro.setText("Tipo de usuário desconhecido.");
                    lblErro.setVisible(true);
                    return;
                }

                if (root != null) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }

            } else {
                lblErro.setText("Email ou senha inválidos");
                lblErro.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            lblErro.setText("Erro ao conectar com o banco de dados.");
            lblErro.setVisible(true);
        }
    }

    public void CriarConta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/CriarConta.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

