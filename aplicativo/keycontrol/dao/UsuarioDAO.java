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

    private static UsuarioDAO singleton;
    
    private UsuarioDAO() {}
    
    public static UsuarioDAO getInstance() {
        if(singleton == null) {
            singleton = new UsuarioDAO();
            return singleton;
        }
        else
            return singleton;
    }
    
    public UsuarioDTO logar(UsuarioDTO usuario) throws PersistenciaException {
        UsuarioDTO usuarioR = null;
        Connection con = null;
        String sql = "SELECT * FROM usuario ";
        sql += "WHERE login = ? ";
        sql += "AND senha = ? ";
        try {
            con = ConexaoUtil.abrirConexao("Login");
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
        String sql = "INSERT INTO usuario (id_usuario, nome, login, senha, tipo) values (NULL, ?,?,?,?)";
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
    public void atualizar(UsuarioDTO u) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Atualizar Usuario");
        List<Object> values = new ArrayList<>();
        String sql = "UPDATE usuario ";
        sql += "SET ";
        boolean ultimo = false;
        Integer count = 1;
        if (u.getNome() != null && !u.getNome().equals("")) {
            ultimo = true;
            sql += "nome = ? ";
            values.add(u.getNome());
            count++;
        }
        if (u.getLogin() != null && !u.getLogin().equals("")) {
            if (ultimo) {
                sql += ", ";
            } else {
                ultimo = true;
            }
            sql += "login = ? ";
            values.add(u.getLogin());
            count++;
        }
        if (u.getSenha() != null && !u.getSenha().equals("")) {
            if (ultimo) {
                sql += ", ";
            } else {
                ultimo = true;
            }
            sql += "senha = ? ";
            values.add(u.getSenha());
            count++;
        }
        if (u.getTipo() != null) {
            if (ultimo) {
                sql += ", ";
            }
            sql += "tipo = ? ";
            values.add(u.getTipo());
            count++;
        }
        sql += "WHERE id_usuario = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(count, u.getId());
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) instanceof Integer) {
                    ps.setInt(i+1, (int) values.get(i));
                } else if (values.get(i) instanceof String) {
                    ps.setString(i+1, (String) values.get(i));
                }
            }
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
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
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
                u.setNome(rs.getString(2));
                u.setLogin(rs.getString(3));
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
        sql += "WHERE id_usuario = ? ";
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
    public List<UsuarioDTO> buscar(UsuarioDTO obj) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Listar Usuarios Filtrado");
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ";
        boolean primeiro = false;
        List<Object> values = new ArrayList<>();
        if (obj.getId() != null) {
            sql += "WHERE id_usuario LIKE ? ";
            primeiro = true;
            values.add(obj.getId());
        }
        if (obj.getLogin() != null) {
            if (primeiro) {
                sql += "AND ";
            } else {
                primeiro = true;
                sql += "WHERE ";
            }
            sql += "login LIKE ? ";
            values.add(obj.getLogin());
        }
        if (obj.getTipo() != null) {
            if (primeiro) {
                sql += "AND ";
            } else {
                primeiro = true;
                sql += "WHERE ";
            }
            sql += "tipo LIKE ? ";
            values.add(obj.getTipo());
        }
        if (obj.getNome() != null) {
            if (primeiro) {
                sql += "AND ";
            } else {
                sql += "WHERE ";
            }
            sql += "nome LIKE ? ";
            values.add(obj.getNome());
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) instanceof Integer) {
                    ps.setInt(i + 1, (int) values.get(i));
                } else if (values.get(i) instanceof String) {
                    ps.setString(i + 1, "%" + (String) values.get(i) + "%");
                }
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
