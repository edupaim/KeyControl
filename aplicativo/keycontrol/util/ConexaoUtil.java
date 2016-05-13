package aplicativo.keycontrol.util;

import java.sql.*;

public class ConexaoUtil {

    private static Connection con;

    public static Connection abrirConexao(String s) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://base2.trevvo.com.br:3306/admin_key";
            String user = "admin_key";
            String senha = "engsoft";
            con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
        } catch (InstantiationException | IllegalAccessException ex) {
        }
        if (con != null) {
            System.out.println("Conexao aberta. [" + s + "]");
        } else {
            System.out.println("Nao foi possivel realizar conexao.");
        }
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
