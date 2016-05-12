package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ChaveDAO implements GenericoDAO<ChaveDTO> {

    // obs: pra que Integer id nos argumentos das funções se o DTO ja vem com o id?
    @Override
    public void atualizar(ChaveDTO obj) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Atualizar Chave");
        List<Object> values = new ArrayList<>();
        boolean primeiro = true;
        String sql = "UPDATE chaves WHERE";
        sql += " id_chave = ?";
        sql += " SET ";
        if (obj.getCapacidade() != null) {
            if (primeiro) {
                sql += "capacidade = ?";
                primeiro = false;
            } else {
                sql += ", capacidade = ?";
            }
            values.add(obj.getCapacidade());
        }
        if (obj.getBeneficiario_id()!= null) {
            if (primeiro) {
                sql += "id_beneficiario = ?";
                primeiro = false;
            } else {
                sql += ", id_beneficiario = ?";
            }
            values.add(obj.getBeneficiario_id());
        }
        if (obj.getSala() != null) {
            if (primeiro) {
                sql += "sala = ?";
                primeiro = false;
            } else {
                sql += ", sala = ?";
            }
            values.add(obj.getSala());
        }
        if (obj.getTipo() != null) {
            if (primeiro) {
                sql += "tipo = ?";
            } else {
                sql += ", tipo = ?";
            }
            values.add(obj.getTipo());
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            for (int i = 2; i <= values.size(); i++) {
                if (values.get(i) instanceof Integer) {
                    ps.setInt(i, (int) values.get(i));
                } else if (values.get(i) instanceof String) {
                    ps.setString(i, (String) values.get(i));
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
    public void inserir(ChaveDTO chave) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Inserir Sala");
        String sql = "INSERT INTO chave (id_chave, sala, capacidade, tipo, id_beneficiario) values (NULL,?,?,?,NULL)";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, chave.getSala());
                ps.setInt(2, chave.getCapacidade());
                ps.setInt(3, chave.getTipo());
                ps.execute();
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }

    @Override
    public void deletar(Integer id) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Deletar Chave");
        String sql = "DELETE FROM sala WHERE id_sala = ?";
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
    public List<ChaveDTO> listarTodos() throws PersistenciaException {
        List<ChaveDTO> retorno = new ArrayList<>();
        Connection con = ConexaoUtil.abrirConexao("Listar Todas Chaves");
        String sql = "SELECT * FROM usuario ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChaveDTO chave = new ChaveDTO();
                chave.setId(rs.getInt(1));
                chave.setSala(rs.getString(2));
                chave.setCapacidade(rs.getInt(3));
                chave.setTipo(rs.getInt(4));
                chave.setBeneficiario_id(rs.getInt(5));
                retorno.add(chave);
            }

        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return retorno;
    }

    @Override
    public ChaveDTO buscarPorId(Integer id) throws PersistenciaException {
        ChaveDTO chave = null;
        Connection con = ConexaoUtil.abrirConexao("Buscar Chave por ID");
        String sql = "SELECT * FROM chaves ";
        sql += "WHERE id_chave = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                chave = new ChaveDTO();
                chave.setId(rs.getInt(1));
                chave.setSala(rs.getString(2));
                chave.setCapacidade(rs.getInt(3));
                chave.setTipo(rs.getInt(4));
                chave.setBeneficiario_id(rs.getInt(5));
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return chave;
    }

    @Override
    public List<ChaveDTO> buscar(ChaveDTO obj) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Buscar chave");
        List<ChaveDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM chaves";
        sql += " WHERE ";
        boolean primeiro = true;
        List<Object> values = new ArrayList<>();

        if (obj.getId() != null) {
            sql += "id_chave LIKE ?";
            primeiro = false;
            values.add(obj.getId());
        }
        if (obj.getCapacidade() != null) {
            if (primeiro) {
                sql += "capacidade LIKE ?";
                primeiro = false;
            } else {
                sql += " AND capacidade LIKE ?";
            }
            values.add(obj.getCapacidade());
        }
        if (obj.getBeneficiario_id()!= null) {
            if (primeiro) {
                sql += "id_beneficiario LIKE ?";
                primeiro = false;
            } else {
                sql += " AND id_beneficiario LIKE ?";
            }
            values.add(obj.getBeneficiario_id());
        }
        if (obj.getSala() != null) {
            if (primeiro) {
                sql += "sala LIKE ?";
                primeiro = false;
            } else {
                sql += " AND sala LIKE ?";
            }
            values.add(obj.getSala());
        }
        if (obj.getTipo() != null) {
            if (primeiro) {
                sql += "tipo LIKE ?";
            } else {
                sql += " AND tipo LIKE ?";
            }
            values.add(obj.getTipo());
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 1; i <= values.size(); i++) {
                if (values.get(i) instanceof Integer) {
                    ps.setInt(i, (int) values.get(i));
                } else if (values.get(i) instanceof String) {
                    ps.setString(i, "%" + (String) values.get(i) + "%");
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ChaveDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)
                ));
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return lista;
    }

}
