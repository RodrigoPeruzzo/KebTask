package sample.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    public void start(Stage KebTask) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLs/Home.fxml")); // Chamar o arquivo //
        KebTask.setTitle("KebTask - My Board");
        KebTask.setResizable(false); // Não permite usuário "maximizar" a tela //
        KebTask.setScene(new Scene(root, 528, 334)); // Deve ser igual ao tamanho do pane //
        KebTask.show();
    }
}
