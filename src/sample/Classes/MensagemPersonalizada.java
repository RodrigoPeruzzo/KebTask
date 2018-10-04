package sample.Classes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MensagemPersonalizada {
    public void EmiteMensagem(String Titulo, String Corpo, String Tipo){
        Alert msgPersonalizada = null;

        switch (Tipo){
            case "info":
                msgPersonalizada = new Alert(Alert.AlertType.INFORMATION);
                break;
            case "error":
                msgPersonalizada = new Alert(Alert.AlertType.ERROR);
                break;
            case "warning":
                msgPersonalizada = new Alert(Alert.AlertType.WARNING);
                break;
        }

        msgPersonalizada.setTitle(Titulo);
        msgPersonalizada.setHeaderText(Corpo);
        msgPersonalizada.showAndWait();
    }

    public boolean EmitePerguntaObjetiva(String title, String cabecalho){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(cabecalho);
        alert.setContentText("");

        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");

        alert.getButtonTypes().setAll(btnSim,btnNao);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == btnSim){
            return true;
        } else {
            return false;
        }
    }
}