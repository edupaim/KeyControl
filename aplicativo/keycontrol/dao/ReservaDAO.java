/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.dao;

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

/**
 *
 * @author Vax
 */
public class ReservaDAO implements GenericoDAO<ReservaDTO> {
    
    private static ReservaDAO singleton;

    private ReservaDAO() {
    }

    public static ReservaDAO getInstance() {
        if (singleton == null) {
            singleton = new ReservaDAO();
            return singleton;
        } else {
            return singleton;
        }
    }
    
    public void atualizar(Integer id_beneficiario, Integer id_chave, Date data_in, Date data_out, Integer horario) {
        
    }

    @Override
    public void inserir(ReservaDTO obj) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Inserir Reserva");
        String sql = "INSERT INTO reserva (id_beneficiario, id_chave, data_entrada, data_saida, id_horario) values (?,?,?,?,?)";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, obj.getId_beneficiario());
                ps.setInt(2, obj.getId_chave());
                ps.setDate(3, new java.sql.Date(obj.getDate_in().getTime()));
                ps.setDate(4, new java.sql.Date(obj.getDate_out().getTime()));
                ps.setInt(5, obj.getHorario());
                ps.execute();
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
    }

    @Override
    public void atualizar(ReservaDTO obj) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Integer id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservaDTO buscarPorId(Integer id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReservaDTO> buscar(ReservaDTO obj) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReservaDTO> listarTodos() throws PersistenciaException {
        List<ReservaDTO> retorno = new ArrayList<>();
        Connection con = ConexaoUtil.abrirConexao("Listar Todas Reservas");
        String sql = "SELECT * FROM reserva ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // (id_beneficiario, id_chave, data_entrada, data_saida, id_horario)
                ReservaDTO reserva = new ReservaDTO(rs.getInt("id_reserva"),
                        rs.getInt("id_beneficiario"),
                        rs.getInt("id_chave"),
                        rs.getDate("data_entrada"),
                        rs.getDate("data_saida"),
                        rs.getInt("id_horario"));
                retorno.add(reserva);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Houve um erro no banco de dados: " + ex.getMessage());
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return retorno;
    }
    
}
