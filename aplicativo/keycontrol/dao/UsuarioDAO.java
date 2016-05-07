package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Edu
 */
public class UsuarioDAO implements GenericoDAO<UsuarioDTO> {

    public UsuarioDTO logar(UsuarioDTO usuario) throws PersistenciaException {
        UsuarioDTO usuarioR = null;
        Connection con = ConexaoUtil.abrirConexao("Login");
        String sql = "SELECT * FROM usuario ";
        sql += "WHERE login = ? ";
        sql += "AND senha = ? ";
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
    public void inserir(UsuarioDTO u) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Inserir Usuario");
        String sql = "INSERT INTO usuario (id, nome, login, senha, tipo) values (NULL, ?,?,?,?)";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, u.getNome());
                ps.setString(2, u.getLogin());
                ps.setString(3, u.getSenha());
                ps.setInt(4, u.getTipo());
                ps.execute();
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }

    @Override
    public void atualizar(Integer id, UsuarioDTO u) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Atualizar Usuario");
        String sql = "UPDATE usuario SET ";
        boolean ultimo = false;
        int cont = 0;
        if (u.getNome() != null && !u.getNome().equals("")) {
            ultimo = true;
            sql += "nome = ? ";
        }
        if (u.getLogin() != null && !u.getLogin().equals("")) {
            if (ultimo) {
                sql += ", ";
            } else {
                ultimo = true;
            }
            sql += "login = ? ";
        }
        if (u.getSenha() != null && !u.getSenha().equals("")) {
            if (ultimo) {
                sql += ", ";
            } else {
                ultimo = true;
            }
            sql += "senha = ? ";
        }
        if (u.getTipo() != null) {
            if (ultimo) {
                sql += ", ";
            }
            sql += "tipo = ? ";
        }
        sql += "WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            if (u.getNome() != null && !u.getNome().equals("")) {
                ps.setString(++cont, u.getNome());
            }
            if (u.getLogin() != null && !u.getLogin().equals("")) {
                ps.setString(++cont, u.getLogin());
            }
            if (u.getSenha() != null && !u.getSenha().equals("")) {
                ps.setString(++cont, u.getSenha());
            }
            if (u.getTipo() != null) {
                ps.setInt(++cont, u.getTipo());
            }
            ps.setInt(++cont, id);
            ps.execute();
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }

    }

    @Override
    public void deletar(Integer id) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Deletar Usuario");
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.execute();
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }

    @Override
    public List<UsuarioDTO> listarTodos() throws PersistenciaException {
        List<UsuarioDTO> retorno = new ArrayList<>();
        Connection con = ConexaoUtil.abrirConexao("Listar Todos Usuarios");
        String sql = "SELECT * FROM usuario ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioDTO u = new UsuarioDTO();
                u.setId(rs.getInt(1));
                u.setLogin(rs.getString(3));
                u.setNome(rs.getString(2));
                u.setSenha(rs.getString(4));
                u.setTipo(rs.getInt(5));
                retorno.add(u);
            }

        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return retorno;
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) throws PersistenciaException {
        UsuarioDTO usuarioR = null;
        Connection con = ConexaoUtil.abrirConexao("Buscar Usuario por ID");
        String sql = "SELECT * FROM usuario ";
        sql += "WHERE id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
    public List<UsuarioDTO> buscar(UsuarioDTO u) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Listar Usuarios Filtrado");
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ";
        boolean ultimo = false;
        int cont = 0;
        if (u.getId() != null) {
            sql += "WHERE id LIKE ? ";
            ultimo = true;
        }
        if (u.getLogin() != null) {
            if (ultimo) {
                sql += "AND ";
            } else {
                sql += "WHERE ";
                ultimo = true;
            }
            sql += "login LIKE ? ";
        }
        if (u.getTipo() != null) {
            if (ultimo) {
                sql += "AND ";
            } else {
                sql += "WHERE ";
                ultimo = true;
            }
            sql += "tipo LIKE ? ";
        }
        if (u.getNome() != null) {
            if (ultimo) {
                sql += "AND ";
            } else {
                sql += "WHERE ";
            }
            sql += "nome LIKE ? ";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (u.getId() != null) {
                ps.setInt(++cont, u.getId());
            }
            if (u.getLogin() != null) {
                ps.setString(++cont, "%" + u.getLogin() + "%");
            }
            if (u.getTipo() != null) {
                ps.setInt(++cont, u.getTipo());
            }
            if (u.getNome() != null) {
                ps.setString(++cont, "%" + u.getNome() + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioDTO aux = new UsuarioDTO();
                aux.setId(rs.getInt(1));
                aux.setNome(rs.getString(2));
                aux.setLogin(rs.getString(3));
                aux.setSenha(rs.getString(4));
                aux.setTipo(rs.getInt(5));
                lista.add(aux);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return lista;
    }

}
