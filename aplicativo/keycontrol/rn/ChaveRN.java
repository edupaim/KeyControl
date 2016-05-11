package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import java.util.List;

public class ChaveRN {
    public ChaveRN() {
        
    }
    
    public void devolucaoChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO chaveDAO;
        List<ChaveDTO> chaves;
        try {
            if(chave.getId() == 0)
                throw new NegocioException("ID não pode ser nulo");
            else {
                chaveDAO = new ChaveDAO();
                if((chaves = chaveDAO.buscarChave(chave)) != null) {
                    chaveDAO.alterarChave(chaves.get(0));
                } else {
                    throw new NegocioException("Não foi encontrado nenhuma chave com tal ID.");
                }
            }
        } catch ( NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public List<ChaveDTO> buscarChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO chaveDAO;
        List<ChaveDTO> chaves;
        try {
            if(chave.getCod().equals("") && chave.getSala().equals("")) {
                throw new NegocioException("msg");
            }
            else {
                chaveDAO = new ChaveDAO();
                if((chaves = chaveDAO.buscarChave(chave)) != null) {
                    return chaves;
                } else {
                    throw new NegocioException("Não foi encontrado nenhuma chave.");
                }
            }
        } catch ( NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
}
