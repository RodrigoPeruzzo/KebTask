package sample.Classes;

import com.mysql.jdbc.Connection;
import org.bouncycastle.util.encoders.UrlBase64;

import java.sql.DriverManager;

public class ConectaMySQL {
    public static java.sql.Connection getConexaoMySQL(){
        java.sql.Connection con = null;

        String driverName = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driverName);

            String MeuServer = "db4free.net:3306";
            String MeuBanco = "desenkebtask";
            String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco;
            String MeuUsuario = "paobatata";
            String MinhaSenha ="141295AB";
/*
            String MeuServer = "sql154.main-hosting.eu";
            String MeuBanco = "u257439930_kebta";
            String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco;
            String MeuUsuario = "u257439930_paoba";
            String MinhaSenha ="rQpjoOgMc50g";*/
/*
            String MeuServer = "mysql796.umbler.com:41890";
            String MeuBanco = "kebtask";
            String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco;
            String MeuUsuario = "trigopaobatata";
            String MinhaSenha ="141295AB";*/

            con = DriverManager.getConnection(UrlConexao,MeuUsuario,MinhaSenha);

            if (con != null){
                System.out.println("Conectado com sucesso.");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (Exception ignored){
            ignored.printStackTrace();
        }

        return con;
    }

    public static boolean FecharConexao(){
        try {
            ConectaMySQL.getConexaoMySQL().close();

            return true;
        } catch (Exception ignored){
            ignored.printStackTrace();

            return false;
        }
    }
}
