package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import sample.Classes.*;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRegistro implements javafx.fxml.Initializable {
    @FXML
    private Button btnConfirmaCadastro;
    @FXML
    private Button btnInsiraFotoRegistro;
    @FXML
    private TextField txNameNickName;
    @FXML
    private TextField txEmailRegistro;
    @FXML
    private PasswordField txPassRegistro;
    @FXML
    private PasswordField txPassRegistroConfirm;
    @FXML
    private ImageView ImagePerfilRegistro;

    public static int IDImagePerfil = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImagePerfilRegistro.setImage(new GetImagens().ImagemUsuario(IDImagePerfil));
    }

    public void handleBtnConfirmaCadastroAction(ActionEvent actionEvent){
        if (txNameNickName.getText().isEmpty()){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Seu nome deve ser informado.","warning");
            return;
        }

        if (txEmailRegistro.getText().isEmpty()){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Seu e-mail deve ser informado.","warning");
            return;
        }

        if (txPassRegistro.getText().isEmpty()){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Sua senha deve ser informada.","warning");
            return;
        }

        if (!txPassRegistro.getText().equals(txPassRegistroConfirm.getText())){
            new MensagemPersonalizada().EmiteMensagem("Confirmação incorreta","Suas senhas não estão iguais.","error");
            return;
        }

        boolean retorno = new ControlaMySQL().RegistroExiste("kt_usuario"," email_usuario = '" + txEmailRegistro.getText() + "' AND status_usuario = 'A'");

        if (retorno){
            new MensagemPersonalizada().EmiteMensagem("Falha no cadastro","E-mail informado já está cadastrado em nosso sistema.","error");
            return;
        }

        retorno = new ControlaMySQL().Inserir("INSERT INTO kt_usuario (nome_usuario, email_usuario, senha_usuario, id_imagem, status_usuario)" +
                " VALUES ('" + txNameNickName.getText() + "','" + txEmailRegistro.getText() + "','" + new GeraHash().GeraHash(txPassRegistro.getText()) + "'," + IDImagePerfil + ",'A')");

        if (retorno){
            new MensagemPersonalizada().EmiteMensagem("Cadastro efetuado com sucesso.","Seja bem vindo " + txNameNickName.getText().toString() + ", você é nosso melhor usuário.","info");

            new AbreTela().Login();

            btnConfirmaCadastro.getScene().getWindow().hide();
        } else {
            new MensagemPersonalizada().EmiteMensagem("Falha ao incluir usuário","Falha ao efetuar seu cadastro. Entre em contato com um administrador.","error");
        }
    }

    public void handleBtnInsiraFotoRegistro(ActionEvent actionEvent){
        new AbreTela().ImagePerfil();
        btnConfirmaCadastro.getScene().getWindow().hide();
    }
}
