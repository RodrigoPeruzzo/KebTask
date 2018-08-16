package sample.Controllers;

import groovy.util.logging.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
