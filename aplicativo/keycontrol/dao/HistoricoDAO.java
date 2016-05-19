package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.ConexaoUtil;
import aplicativo.keycontrol.util.ThreadHoraUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/*
 * @author Edu
 */
public class HistoricoDAO {

    private static HistoricoDAO singleton;

    private HistoricoDAO() {
    }

    public static HistoricoDAO getInstance() {
        if (singleton == null) {
            singleton = new HistoricoDAO();
            return singleton;
        } else {
            return singleton;
        }
    }
    
    public void inserir(Integer b, Integer c, Integer tipo) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Inserir Historico");
        String sql = "INSERT INTO historico (id_historico, id_beneficiario, data_operacao, id_chave, tipo_operacao) values (NULL, ?,?,?,?)";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, b);
                ps.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
                ps.setInt(3, c);
                ps.setInt(4, tipo);
                ps.execute();
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }

}
