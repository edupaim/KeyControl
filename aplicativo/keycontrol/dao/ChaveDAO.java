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
public class ChaveDAO {
    
    /*
        Singleton
    */    
    private ChaveDAO() {}
    
    // obs: pra que Integer id nos argumentos das funções se o DTO ja vem com o id?
    public static void atualizar(ChaveDTO chave) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Atualizar Chave");
        String sql = "UPDATE chaves WHERE";
        sql += " id = ?";
        sql += " SET";
        sql += " cod = ?"; // string
        sql += ", sala = ?"; // string
        sql += ", capacidade = ?"; // int
        sql += ", andar = ?"; // string
        sql += ", tipo = ?"; // string (char)
        sql += ", estado = ?"; // string
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chave.getId());
            ps.setString(2, chave.getCod());
            ps.setString(3, chave.getSala());
            ps.setInt(4, chave.getCapacidade());
            ps.setString(5, chave.getAndar());
            ps.setString(6, chave.getTipo());
            ps.setString(7, chave.getEstado());
            ps.execute();
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }
    
    public void inserir(ChaveDTO obj) throws PersistenciaException {
        throw new PersistenciaException("Not supported yet.");
    }

    public void atualizar(Integer id, ChaveDTO obj) throws PersistenciaException {
        throw new PersistenciaException("Not supported yet.");
    }

    public void deletar(Integer id) throws PersistenciaException {
        throw new PersistenciaException("Not supported yet.");
    }

    public List<ChaveDTO> listarTodos() throws PersistenciaException {
        throw new PersistenciaException("Not supported yet.");
    }

    public ChaveDTO buscarPorId(Integer id) throws PersistenciaException {
        throw new PersistenciaException("Not supported yet.");
    }

    public static List<ChaveDTO> buscar(ChaveDTO obj) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Buscar chave");
        List<ChaveDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM chaves";
        sql += " WHERE ";
        boolean primeiro = true;
        List<Object> values = new ArrayList<>();
        
        if(obj.getId() != null) {
            sql += "id = ?";
            primeiro = false;
            values.add(obj.getId());
        }
        if(obj.getAndar() != null) {
            if(primeiro) {
                sql += "andar = ?";
                primeiro = false;
            }
            else
                sql += ", AND andar = ?";
            values.add(obj.getAndar());
        }
        if(obj.getCapacidade() != null) {
            if(primeiro) {
                sql += "capacidade = ?";
                primeiro = false;
            }
            else
                sql += ", AND capacidade = ?";
            values.add(obj.getCapacidade());
        }
        if(obj.getCod() != null) {
            if(primeiro) {
                sql += "cod = ?";
                primeiro = false;
            }
            else
                sql += ", AND cod = ?";
            values.add(obj.getCod());
        }
        if(obj.getEstado() != null) {
            if(primeiro) {
                sql += "estado = ?";
                primeiro = false;
            }
            else
                sql += ", AND estado = ?";
             values.add(obj.getEstado());
        }
        if(obj.getSala() != null) {
            if(primeiro) {
                sql += "sala = ?";
                primeiro = false;
            }
            else
                sql += ", AND sala = ?";
            values.add(obj.getSala());
        }
        if(obj.getTipo() != null) {
            if(primeiro) {
                sql += "tipo = ?";
                primeiro = false;
            }
            else
                sql += ", AND tipo = ?";
            values.add(obj.getTipo());
        }
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for(int i = 1; i <= values.size(); i++) {
                if(values.get(i) instanceof Integer)
                    ps.setInt(i, (int) values.get(i));
                if(values.get(i) instanceof String || values.get(i) instanceof Character)
                    ps.setString(i, (String) values.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ChaveDTO(rs.getInt(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getInt(4),
                                       rs.getString(5),
                                       rs.getString(6),
                                       rs.getString(7)
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
