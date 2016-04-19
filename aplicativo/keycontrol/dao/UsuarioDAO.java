package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
 * @author Edu
 */
public class UsuarioDAO implements GenericoDAO<Object>{
    
    public UsuarioDTO logar(UsuarioDTO usuario) throws PersistenciaException {
        UsuarioDTO usuarioR = null;
        Connection con = ConexaoUtil.abrirConexao("Login");
        String sql = "select * from usuario ";
        sql += "where login = ? ";
        sql += "and senha = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioR = new UsuarioDTO();
                usuarioR.setId(rs.getInt(1));
                usuarioR.setLogin(rs.getString(3));
                usuarioR.setNome(rs.getString(2));
                usuarioR.setSenha(rs.getString(4));
                usuarioR.setTipo(rs.getInt(5));
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return usuarioR;
    }

    @Override
    public void inserir(Object obj) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Integer id, Object obj) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Integer id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorId(Integer id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
