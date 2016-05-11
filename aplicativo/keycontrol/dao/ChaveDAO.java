package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Vax
 */
public class ChaveDAO {

    public List<ChaveDTO> buscarChave(ChaveDTO chave) throws PersistenciaException {
        throw new PersistenciaException("Not supported yet.");
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
        return null;
    }

    public ChaveDTO buscarPorId(Integer id) throws PersistenciaException {
        return null;
    }
    
    public List<ChaveDTO> buscar(ChaveDTO obj) throws PersistenciaException {
        return null;
    }

    public void alterarChave(ChaveDTO get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
