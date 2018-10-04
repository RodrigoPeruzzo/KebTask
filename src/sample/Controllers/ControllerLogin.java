package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Classes.*;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLogin implements javafx.fxml.Initializable {
    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistrarMe;

    @FXML
    private TextField txEmailUser;

    @FXML
    private PasswordField txSenhaUser;

    // kt_usuarios
    // id_usuario // nome_apelido // email // senha // status //

    public void handleBtnEntrarAction(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (txEmailUser.getText().toString().isEmpty()){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Seu e-mail deve ser informado.","warning");
            return;
        }

        if (txSenhaUser.getText().toString().isEmpty()){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Sua senha deve ser informada.","warning");
            return;
        }

        ControlaMySQL control = new ControlaMySQL();

        ResultSet rs = control.Select("SELECT id_usuario, nome_usuario FROM kt_usuario WHERE email_usuario = '" + txEmailUser.getText().toString() + "' AND senha_usuario = '" + new GeraHash().GeraHash(txSenhaUser.getText().toString()) + "'");

        while (rs.next()){
            if (!rs.getString(2).isEmpty()){
                Sessao.setIdUsuario(rs.getInt(1));

                new AbreTela().Home();
                btnEntrar.getScene().getWindow().hide();

                return;
            }
        }

        new MensagemPersonalizada().EmiteMensagem("Falha ao efetuar o login","Desculpe, não reconheci seu usuário.","error");
    }

    public void handleBtnRegistrarMeAction(javafx.event.ActionEvent actionEvent){
        new AbreTela().Registro();

        btnRegistrarMe.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileInputStream inp = new FileInputStream("Icones/IconEntrar.png");

            Image image = new Image(inp);
            ImageView imgView = new ImageView(image);
            imgView.setFitHeight(15);
            imgView.setFitWidth(15);

            btnEntrar.setGraphic(imgView);
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }
}
