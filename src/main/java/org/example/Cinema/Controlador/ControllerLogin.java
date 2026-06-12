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

        // Instancia o DAO para buscar no MySQL
        UsuarioDao dao = new UsuarioDao();

        try {
            // Tenta autenticar o usuário com os dados digitados
            Usuario usuarioLogado = dao.autenticar(email, senha);

            if (usuarioLogado != null) {
                // Pega o tipo do usuário vindo do banco e joga para minúsculo para evitar erros
                String tipo = usuarioLogado.getTipo().toLowerCase();

                // Decide qual tela abrir baseado no tipo salvo no banco
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

                // Se encontrou a tela com sucesso, muda de cena
                if (root != null) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }

            } else {
                // Caso o método autenticar retorne null (usuário ou senha errados)
                lblErro.setText("Email ou senha inválidos");
                lblErro.setVisible(true);
            }

        } catch (Exception e) {
            // Caso ocorra algum erro de conexão com o MySQL (banco desligado, senha do banco errada, etc.)
            e.printStackTrace(); // Mostra o erro detalhado no console para você debugar
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

