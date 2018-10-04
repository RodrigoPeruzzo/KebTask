package sample.Classes;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {
    public static java.sql.Connection getConexaoMySQL(){
        java.sql.Connection con = null;

        try {
            //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // Deve informar os dados do banco //
            String MeuServer = "localhost";
            String MeuBanco = "db_kebtask";
            //String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco + "?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
            String UrlConexao = "jdbc:mysql://" + MeuServer + "/" + MeuBanco + "?autoReconnect=true&useSSL=false";
            String MeuUsuario = "root";
            String MinhaSenha ="";
            // Dados em branco para não ficar visível a todos //

            con = DriverManager.getConnection(UrlConexao,MeuUsuario,MinhaSenha);
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
