package aplicativo.keycontrol.util;

import java.sql.*;

public class ConexaoUtil {

    private static Connection con;

    public static Connection abrirConexao(String s) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/keycontrol";
            String user = "root";
            String senha = "1305";
            con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
        } catch (InstantiationException | IllegalAccessException ex) {
        }
        System.out.println("Conexao aberta. [" + s + "]");
        return con;
    }

    public static void fecharConexao(Connection con) {
        try {
            con.close();
            System.out.println("Conexao fechada.");
        } catch (SQLException ex) {
        }
    }
}