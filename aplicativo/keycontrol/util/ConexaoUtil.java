package aplicativo.keycontrol.util;

import aplicativo.keycontrol.exception.PersistenciaException;
import java.sql.*;

public class ConexaoUtil {

    private static Connection con;

    public static Connection abrirConexao(String s) throws PersistenciaException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/KeyControl";
            String user = "root";
            String senha = "1305";
            con = DriverManager.getConnection(url, user, senha);
            System.out.printf("Conexão aberta [" + s + "]\n");
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException("Não foi possivel realizar a conexão. [" + s + "]");
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new PersistenciaException("Ocorreu um erro ao realizar a conexão. [" + s + "]");
        }
        return con;

    }

    public static void fecharConexao(Connection con) throws PersistenciaException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new PersistenciaException("Conexão não encerrada com sucesso");
        }
    }
}
