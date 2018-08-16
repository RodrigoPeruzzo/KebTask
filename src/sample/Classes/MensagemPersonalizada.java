package sample.Classes;

import javafx.scene.control.Alert;

public class MensagemPersonalizada {
    public void EmiteMensagem(String Titulo, String Corpo){
        Alert msgCadastro = new Alert(Alert.AlertType.INFORMATION);

        msgCadastro.setTitle(Titulo);
        msgCadastro.setHeaderText(Corpo);
        msgCadastro.showAndWait();
    }
}