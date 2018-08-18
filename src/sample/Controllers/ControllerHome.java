package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import sample.Classes.AbreTela;

public class ControllerHome extends Pane {
    @FXML
    private Button btnSairHome;

    public void handleBtnSairHomeAction(ActionEvent actionEvent) {
        new AbreTela().Login();
        btnSairHome.getScene().getWindow().hide();
    }

    public void handleBtnAdicionarHomeAction(ActionEvent actionEvent){
        new AbreTela().Adicionar();
    }

    public void handleBtnConsultarHomeAction(ActionEvent actionEvent){
        new AbreTela().Consulta();
    }
}
