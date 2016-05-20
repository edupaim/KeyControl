/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.AlunoDTO;

import aplicativo.keycontrol.dto.IBeneficiarioDTO;
import aplicativo.keycontrol.dto.ProfessorDTO;
import aplicativo.keycontrol.dto.UsuarioDTO;
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
 * @author ramon
 */
public class BeneficiarioDAO implements GenericoDAO<IBeneficiarioDTO> {

    private static BeneficiarioDAO singleton;

    private BeneficiarioDAO() {
    }

    public static BeneficiarioDAO getInstance() {
        if (singleton == null) {
            singleton = new BeneficiarioDAO();
            return singleton;
        } else {
            return singleton;
        }
    }

    @Override
    public void inserir(IBeneficiarioDTO beneficiario) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Inserir Beneficiario");
        String sql = "INSERT INTO beneficiario (id_beneficiario, nome, matricula, tipo) values (NULL,?,?,?)";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, beneficiario.getNome());
                ps.setString(2, beneficiario.getMatricula());
                int tipo;
                if (beneficiario instanceof AlunoDTO) {
                    tipo = 1;
                } else {
                    tipo = 2;
                }
                ps.setInt(3, tipo);
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
        Connection con = ConexaoUtil.abrirConexao("Deletar Beneficiario");
        String sql = "DELETE FROM beneficiario WHERE id_beneficiario = ?";
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
    public List<IBeneficiarioDTO> buscar(IBeneficiarioDTO b) throws PersistenciaException {
        return new ArrayList<>();
    }

    @Override
    public IBeneficiarioDTO buscarPorId(Integer id) throws PersistenciaException {
        IBeneficiarioDTO benefR = null;
        Connection con = ConexaoUtil.abrirConexao("Buscar Usuario por ID");
        String sql = "SELECT * FROM beneficiario ";
        sql += "WHERE id_beneficiario = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("tipo") == 0) {
                    benefR = new AlunoDTO();
                } else {
                    benefR = new ProfessorDTO();
                }
                benefR.setId(rs.getInt("id_beneficiario"));
                benefR.setNome(rs.getString("nome"));
                benefR.setMatricula(rs.getString("matricula"));
                return benefR;
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }
        return benefR;
    }

    public IBeneficiarioDTO buscarPorMatricula(String matricula) throws PersistenciaException {
        Connection con = ConexaoUtil.abrirConexao("Buscar Beneficiario por Matricula");
        String sql = "SELECT * FROM beneficiario ";
        sql += "WHERE matricula = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                IBeneficiarioDTO beneficiario;
                if (rs.getInt("tipo") == 0) {
                    beneficiario = new AlunoDTO();
                } else {
                    beneficiario = new ProfessorDTO();
                }
                beneficiario.setId(rs.getInt("id_beneficiario"));
                beneficiario.setNome(rs.getString("nome"));
                beneficiario.setMatricula(rs.getString("matricula"));
                return beneficiario;
            }
            throw new PersistenciaException("Beneficiario inexistente");
        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage(), ex);
        } finally {
            ConexaoUtil.fecharConexao(con);
        }

    }

    @Override
    public List<IBeneficiarioDTO> listarTodos() throws PersistenciaException {
        return new ArrayList();
    }

    @Override
    public void atualizar(IBeneficiarioDTO beneficiario) throws PersistenciaException {

    }
}
