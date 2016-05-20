/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.BeneficiarioDAO;
import aplicativo.keycontrol.dto.IBeneficiarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;

/**
 *
 * @author ramon
 */
public class BeneficiarioRN {
    
    private static BeneficiarioRN singleton;
    
    private BeneficiarioRN() {}
    
    public static BeneficiarioRN getInstance() {
        if(singleton == null) {
            singleton = new BeneficiarioRN();
            return singleton;
        }
        else
            return singleton;
    }
    
    public void inserir(IBeneficiarioDTO beneficiario) throws NegocioException{
        try {
            if(beneficiario.getMatricula().length()==0)
                throw new NegocioException("Matricula obrigatória.");
            if(beneficiario.getNome().length()==0)
                throw new NegocioException("Nome obrigatório.");
            BeneficiarioDAO DAO = BeneficiarioDAO.getInstance();
            DAO.inserir(beneficiario);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    
    public IBeneficiarioDTO buscarPorMatricula(String matricula) throws NegocioException{
        try {
            if(matricula.length() > 0) {
                return BeneficiarioDAO.getInstance().buscarPorMatricula(matricula);
            } else {
                throw new NegocioException("Matricula obrigatória.");
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    
    public IBeneficiarioDTO buscarPorId(Integer id) throws NegocioException {
        try {
            if (id < 0) {
                throw new NegocioException("ID inválido.");
            }
            return BeneficiarioDAO.getInstance().buscarPorId(id);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
}
