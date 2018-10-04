package sample.Classes;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;

public class GetImagens {
    public Image ImagemUsuario(int id_image){
        String nomeImagem = String.valueOf(id_image);

        if (id_image == 0){
            nomeImagem = "user.png";
        } else {
            nomeImagem += ".jpg";
        }

        try {
            FileInputStream inp = new FileInputStream("src/imagens/" + nomeImagem);

            Image image = new Image(inp);

            return image;
        } catch (IOException e){
            e.printStackTrace();

            return null;
        }
    }
}
