package sample.Classes;

import java.security.MessageDigest;

public class GeraHash {

    public String GeraHash(String texto){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes());
            return md.digest().toString();
        } catch (Exception ignored){
            ignored.printStackTrace();
        }

        return null;
    }
}
