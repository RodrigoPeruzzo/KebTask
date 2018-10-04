package sample.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlaMySQL {
    public boolean Inserir(String sql){
        Connection con = ConectaMySQL.getConexaoMySQL();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            if (!ps.execute()){
                // Executou com sucesso //
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean Update(String sql){
        Connection con = ConectaMySQL.getConexaoMySQL();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            if (ps.execute()){
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }
        } catch (Exception ignored){
            ignored.printStackTrace();
        }

        return false;
    }

    public boolean Delete(String sql){
        Connection con = ConectaMySQL.getConexaoMySQL();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            if (ps.execute()){
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public ResultSet Select(String sql){
        Connection con = ConectaMySQL.getConexaoMySQL();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean RegistroExiste(String tabela, String campoFiltro){
        ResultSet rs = Select("SELECT 1 FROM " + tabela + " WHERE " + campoFiltro);

        System.out.println("1");

        try {
            while (rs.next()) {
                System.out.println("2");
                return true;
            }
            System.out.println("3");
        } catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("4");

        return false;
    }
}
