package sample.Classes;

import java.sql.DriverManager;

public class ConectaMySQL {
    public static java.sql.Connection getConexaoMySQL(){
        java.sql.Connection con = null;

        String driverName = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driverName);

            /*// Deve informar os dados do banco //
            String MeuServer = "";
            String MeuBanco = "";
            String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco;
            String MeuUsuario = "";
            String MinhaSenha ="";
            // Dados em branco para não ficar visível a todos //*/

            String MeuServer = "sql154.main-hosting.eu";
            String MeuBanco = "u257439930_kebta";
            String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco;
            String MeuUsuario = "u257439930_paoba";
            String MinhaSenha ="rQpjoOgMc50g";

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
