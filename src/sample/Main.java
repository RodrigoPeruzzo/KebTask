package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Classes.AbreTela;

public class Main extends Application {

    @Override
    public void start(Stage KebTask) throws Exception{
        AbreTela AT = new AbreTela();

        AT.setKebTask(KebTask);
        AT.Login();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
