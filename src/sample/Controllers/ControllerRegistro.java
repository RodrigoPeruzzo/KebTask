package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Classes.AbreTela;
import sample.Classes.MensagemPersonalizada;

public class ControllerRegistro {
    @FXML
    private Button btnConfirmaCadastro;
    @FXML
    private TextField txNameNickName;

    public void handleBtnConfirmaCadastroAction(ActionEvent actionEvent){
        new MensagemPersonalizada().EmiteMensagem("Cadastro de novo participante.","Cadastro do participante (" + txNameNickName.getText().toString() + ") efetuado com sucesso.");

        new AbreTela().Login();

        btnConfirmaCadastro.getScene().getWindow().hide();
    }
}
