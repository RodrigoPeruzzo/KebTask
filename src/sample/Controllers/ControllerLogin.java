package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Classes.AbreTela;

public class ControllerLogin {
    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistrarMe;

    public void handleBtnEntrarAction(javafx.event.ActionEvent actionEvent) {
        new AbreTela().Home();

        btnEntrar.getScene().getWindow().hide();
    }

    public void handleBtnRegistrarMeAction(javafx.event.ActionEvent actionEvent){
        new AbreTela().Registro();

        btnRegistrarMe.getScene().getWindow().hide();
    }
}
