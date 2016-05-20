package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.HistoricoDTO;
import aplicativo.keycontrol.dto.ReservaDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    public List<HistoricoDTO> listarTodos() throws PersistenciaException {
        List<HistoricoDTO> retorno = new ArrayList<>();
        Connection con = ConexaoUtil.abrirConexao("Listar Todas Reservas");
        String sql = "SELECT * FROM historico ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // (id_beneficiario, id_chave, data_entrada, data_saida, id_horario)
                HistoricoDTO historico = new HistoricoDTO(rs.getInt("id_historico"),
                ChaveDAO.getInstance().buscarPorId(rs.getInt("id_chave")),
                BeneficiarioDAO.getInstance().buscarPorId(rs.getInt("id_beneficiario")),
                rs.getTimestamp("data_operacao"),
                rs.getInt("tipo_operacao"));
                retorno.add(historico);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Houve um erro no banco de dados: " + ex.getMessage());
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return retorno;
    }

}
