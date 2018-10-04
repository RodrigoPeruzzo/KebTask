package sample.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.ControllerAdicionar;
import sample.Controllers.ControllerConsulta;
import sample.Controllers.ControllerHome;
import sample.Controllers.ControllerTarefa;

import java.io.IOException;

public class AbreTela {
    static Stage KebTask;

    public void setKebTask(Stage stage){
        KebTask = stage;
    }

    public void Login(){
        try {
            Parent LoginScreen = FXMLLoader.load(getClass().getClassLoader().getResource("sample/FXMLs/Login.fxml"));

            KebTask.setTitle("KebTask - Faça seu login");
            KebTask.setResizable(false);
            KebTask.setScene(new Scene(LoginScreen,371,316));
            KebTask.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void Home(){
        try {
            FXMLLoader Home = new FXMLLoader(getClass().getClassLoader().getResource("sample/FXMLs/Home.fxml"));

            Parent HomeScreen = (Parent) Home.load();
            Stage stage = new Stage();
            stage.setTitle("KebTask - My Board");
            stage.setResizable(false);
            stage.setScene(new Scene(HomeScreen));
            stage.show();
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }

    public void Registro(){
        try {
            FXMLLoader RegistrarMe = new FXMLLoader(getClass().getClassLoader().getResource("sample/FXMLs/Registro.fxml"));

            Parent RegistroScreen = (Parent) RegistrarMe.load();
            Stage stage = new Stage();
            stage.setTitle("KebTask - Registre-se");
            stage.setResizable(false);
            stage.setScene(new Scene(RegistroScreen));
            stage.show();
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }
    public void Consulta(ControllerHome.Callback callback){

        try {
            FXMLLoader Consulta = new FXMLLoader(getClass().getClassLoader().getResource("sample/FXMLs/Consulta.fxml"));
            Parent ConsultaScreen = (Parent) Consulta.load();
            Stage stage = new Stage();
            stage.setTitle("KebTask - Consulta");
            stage.setResizable(false);
            stage.setScene(new Scene(ConsultaScreen));
            stage.show();

            ControllerConsulta c = Consulta.getController();
            c.setCallback(callback);

        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }

    public void Adicionar(ControllerHome.Callback callback){
        try {
            FXMLLoader Adicionar = new FXMLLoader(getClass().getClassLoader().getResource("sample/FXMLs/Adicionar.fxml"));

            Parent AdicionarScreen = (Parent) Adicionar.load();
            Stage stage = new Stage();
            stage.setTitle("KebTask - Nova tarefa");
            stage.setResizable(false);
            stage.setScene(new Scene(AdicionarScreen));
            stage.show();

            ControllerAdicionar c = Adicionar.getController();
            c.setCallback(callback);

        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }

    public void Tarefa(ControllerHome.Callback callback){
        try {
            FXMLLoader Tarefa = new FXMLLoader(getClass().getClassLoader().getResource("sample/FXMLs/Tarefa.fxml"));

            Parent TarefaScreen = (Parent) Tarefa.load();
            Stage stage = new Stage();
            stage.setTitle("KebTask - Tarefa");
            stage.setResizable(false);
            stage.setScene(new Scene(TarefaScreen));
            stage.show();

            ControllerTarefa c = Tarefa.getController();
            c.setCallback(callback);
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }

    public void ImagePerfil(){
        try {
            FXMLLoader Image = new FXMLLoader(getClass().getClassLoader().getResource("sample/FXMLs/ImagePerfil.fxml"));

            Parent ImageScreen = (Parent) Image.load();
            Stage stage = new Stage();
            stage.setTitle("KebTask - Imagens para perfil");
            stage.setResizable(false);
            stage.setScene(new Scene(ImageScreen));
            stage.show();
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }
}
