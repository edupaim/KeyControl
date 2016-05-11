package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import java.util.List;

public class ChaveRN {
    /*
        Singleton
    */
    private ChaveRN() {}
    
    /*
        Checa se o ID é valido, lista todas as chaves com o ID fornecido pelo objeto ChaveDTO,
        como só existe uma, modifica o objeto na parte de estado de "Não disponível" para "Disponível"
        executa uma alteração na db com os novos valores.
    */
    public static void devolucaoChave(ChaveDTO chave) throws NegocioException {
        List<ChaveDTO> chaves;
        try {
            if(chave.getId() != null) {
                if((chaves = ChaveDAO.buscar(chave)).size() > 0) {
                    ChaveDTO alt_chave = chaves.get(0);
                    alt_chave.setEstado("Disponível");
                    ChaveDAO.atualizar(alt_chave);
                } else {
                    throw new NegocioException("Não foi encontrado nenhuma chave com tal ID.");
                }
            } else throw new NegocioException("ID inválido.");
        } catch ( NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    /*
        Checa se é ambos os campos estão nulos, se não, realiza uma busca na DB
        para retornar todas as chaves que tem os campos coincidindo.
    */
    public static List<ChaveDTO> buscarChave(ChaveDTO chave) throws NegocioException {
        List<ChaveDTO> chaves;
        try {
            if(chave.getCod() != null || chave.getSala() != null) {
                if((chaves = ChaveDAO.buscar(chave)).size() > 0) {
                    return chaves;
                } else {
                    throw new NegocioException("Não foi encontrado nenhuma chave.");
                }
            }
            else {
                throw new NegocioException("Ambos campos nulos.");
            }
        } catch ( NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
}
