package sample.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Classes.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerImagePerfil implements javafx.fxml.Initializable {
    @FXML
    ImageView ImagePerfil1;
    @FXML
    ImageView ImagePerfil2;
    @FXML
    ImageView ImagePerfil3;
    @FXML
    ImageView ImagePerfil4;
    @FXML
    ImageView ImagePerfil5;
    @FXML
    ImageView ImagePerfil6;

    public static boolean AtualizaTabela = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImagePerfil1.setImage(new GetImagens().ImagemUsuario(1));
        ImagePerfil2.setImage(new GetImagens().ImagemUsuario(2));
        ImagePerfil3.setImage(new GetImagens().ImagemUsuario(3));
        ImagePerfil4.setImage(new GetImagens().ImagemUsuario(4));
        ImagePerfil5.setImage(new GetImagens().ImagemUsuario(5));
        ImagePerfil6.setImage(new GetImagens().ImagemUsuario(6));
    }

    public void onMouseClickedIMG1(MouseEvent mouseEvent){
        ControllerRegistro.IDImagePerfil = 1;

        AtualizaImagemTabela();
    }

    public void onMouseClickedIMG2(MouseEvent mouseEvent){
        ControllerRegistro.IDImagePerfil = 2;

        AtualizaImagemTabela();
    }

    public void onMouseClickedIMG3(MouseEvent mouseEvent){
        ControllerRegistro.IDImagePerfil = 3;

        AtualizaImagemTabela();
    }

    public void onMouseClickedIMG4(MouseEvent mouseEvent){
        ControllerRegistro.IDImagePerfil = 4;

        AtualizaImagemTabela();
    }

    public void onMouseClickedIMG5(MouseEvent mouseEvent){
        ControllerRegistro.IDImagePerfil = 5;

        AtualizaImagemTabela();
    }

    public void onMouseClickedIMG6(MouseEvent mouseEvent){
        ControllerRegistro.IDImagePerfil = 6;

        AtualizaImagemTabela();
    }

    public void handleOnActionBtnVoltarImagePerfil(ActionEvent actionEvent){
        if (AtualizaTabela) {
            new AbreTela().Registro();
        } else {
            new AbreTela().Home();
        }

        ImagePerfil1.getScene().getWindow().hide();
    }

    private void AtualizaImagemTabela(){
        if (AtualizaTabela){
            new ControlaMySQL().Update("UPDATE kt_usuario SET id_imagem = '" + ControllerRegistro.IDImagePerfil + "' WHERE id_usuario = " + Sessao.getIdUsuario());

            ImagePerfil1.getScene().getWindow().hide();
            new AbreTela().Home();
        } else {
            ImagePerfil1.getScene().getWindow().hide();
            new AbreTela().Registro();
        }
    }

}
