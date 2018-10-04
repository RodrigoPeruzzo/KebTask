package sample.Classes;

public class Sessao {
    private static int idUsuario = 0;

    public static int getIdUsuario(){
        return idUsuario;
    }

    public static void setIdUsuario(int idUser){
        idUsuario = idUser;
    }
}
