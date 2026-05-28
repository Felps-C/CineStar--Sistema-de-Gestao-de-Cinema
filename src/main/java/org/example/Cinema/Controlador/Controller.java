package org.example.Cinema.Controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller {

    public void iniciarTela(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

//package org.example.Cinema.Controlador;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import java.io.IOException;
//
//public class Controller {
//
//    // Este método DEVE ter exatamente o mesmo nome definido no 'onAction' do seu FXML (#iniciarTela)
//    @FXML
//    private void iniciarTela(ActionEvent event) {
//        System.out.println("Botão Iniciar clicado com sucesso!");
//
//        try {
//            // Substitua "principal.fxml" pelo nome real do arquivo FXML da sua próxima tela (ex: "menu.fxml", "vendas.fxml")
//            // Certifique-se de que o caminho do recurso está correto em relação à pasta de resources
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Cinema/Login.fxml"));
//            Parent root = loader.load();
//
//            // Obtém o Stage (janela) atual a partir do evento do botão
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            // Define a nova cena na janela e a exibe
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException e) {
//            System.err.println("Erro ao carregar a próxima tela FXML. Verifique se o caminho do arquivo está correto.");
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            System.err.println("Arquivo FXML não encontrado! Verifique se ele está na pasta de resources correta.");
//            e.printStackTrace();
//        }
//    }
//}