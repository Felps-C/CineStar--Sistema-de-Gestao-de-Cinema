package org.example.Cinema.Controlador;

import dao.ClienteDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import org.example.Cinema.Model.Clientes;
import org.example.Cinema.Model.Produto;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerCriarConta {

    public void criarConta(ActionEvent event)throws IOException {

    }
    public void voltarLogin(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/Cinema/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}


//    private Clientes clienteSelecionado;
//    @FXML
//    private ListView<Clientes> lvClientes;
//
//    public void initialize() {
//
//        ClienteDao clienteDao = new ClienteDao();
//        lvClientes.getItems().addAll(clienteDao.findAll());
//
//        lvClientes.getSelectionModel().selectedItemProperty().addListener(
//                (obs, oldV, newV) -> clienteSelecionado = newV
//        );
//    }
//    ArrayList <String> list = new ArrayList<>();
//    private ListView<> lvCliente;