package sample.Controllers;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jvnet.ws.wadl.Resource;
import sample.Classes.AbreTela;
import sample.Classes.ConectaMySQL;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLogin implements javafx.fxml.Initializable {
    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistrarMe;

    // kt_usuarios
    // id_usuario // nome_apelido // email // senha // status //

    public void handleBtnEntrarAction(javafx.event.ActionEvent actionEvent) {
        Connection con = (Connection) ConectaMySQL.getConexaoMySQL();

        try (PreparedStatement st = (PreparedStatement) con.prepareStatement("INSERT INTO kt_usuarios (nome_apelido,email,senha,status) " +
                "VALUES ('Rodrigo Peruzzo','rodrigoperuzzo2013@gmail.com','rodrigo','A')")){
            st.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //new AbreTela().Home();
        //btnEntrar.getScene().getWindow().hide();
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
