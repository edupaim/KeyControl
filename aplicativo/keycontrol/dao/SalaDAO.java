package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.SalaDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/*
 * @author Edu
 */
public class SalaDAO implements GenericoDAO<SalaDTO>{

    @Override
    public void inserir(SalaDTO sala) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Inserir Sala");
        String sql = "INSERT INTO usuario (id, numero, pavilhao) values (NULL, ?,?)";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, sala.getNumero());
                ps.setString(2, sala.getPavilhao());
                ps.execute();
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }
    
    @Override
    public void atualizar(Integer id, SalaDTO obj) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deletar(Integer id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SalaDTO> listarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SalaDTO buscarPorId(Integer id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SalaDTO> buscar(SalaDTO obj) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
