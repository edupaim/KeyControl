package aplicativo.keycontrol.util;

import aplicativo.keycontrol.exception.PersistenciaException;
import java.sql.*;

public class ConexaoUtil {
    
    private static Connection con;
    
    public static Connection abrirConexao(String s) throws PersistenciaException  {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://base2.trevvo.com.br:3306/admin_key";
            String user = "admin_key";
            String senha = "engsoft";
            con = DriverManager.getConnection(url, user, senha);
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
