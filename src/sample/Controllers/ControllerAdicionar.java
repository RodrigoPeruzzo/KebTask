package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class ControllerAdicionar {
    @FXML
    private Button btnAdicionar;

    public void handleBtnAdicionarAction(javafx.event.ActionEvent actionEvent) {
        btnAdicionar.getScene().getWindow().hide();
    }
}
