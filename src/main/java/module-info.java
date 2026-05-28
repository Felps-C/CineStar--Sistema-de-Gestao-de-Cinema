module org.example.Cinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.example.Cinema to javafx.fxml;
    exports org.example.Cinema;
    exports org.example.Cinema.Controlador;
    opens org.example.Cinema.Controlador to javafx.fxml;
    exports org.example.Cinema.Banco_De_Dados;
    opens org.example.Cinema.Banco_De_Dados to javafx.fxml;
}