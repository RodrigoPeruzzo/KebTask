package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class ControllerConsulta {
    @FXML
    private Button btnPesquisar;

    public void handleBtnPesquisarAction(javafx.event.ActionEvent actionEvent) {
        btnPesquisar.getScene().getWindow().hide();
    }
}
