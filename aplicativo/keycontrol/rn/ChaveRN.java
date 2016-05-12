package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.MensagensUtil;
import java.util.List;

public class ChaveRN {
    /*
     Singleton
     */

    private ChaveRN() {
    }

    /*
     Checa se o ID é valido, modifica o objeto na parte de estado de "Não disponível" para "Disponível"
     executa uma alteração na db com os novos valores.
     */
    public static void devolucaoChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO DAO = new ChaveDAO();
        try {
            if (chave.getId() != null) {
                chave.setEstado("Disponível");
                DAO.atualizar(chave);
            } else {
                throw new NegocioException("ID inválido.");
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    /*
     Checa se é ambos os campos estão nulos, se não, realiza uma busca na DB
     para retornar todas as chaves que tem os campos coincidindo.
     */
    public static List<ChaveDTO> buscarChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO DAO = new ChaveDAO();
        List<ChaveDTO> chaves;
        try {
            if (chave.getCod() != null || chave.getSala() != null) {
                if ((chaves = DAO.buscar(chave)).size() > 0) {
                    return chaves;
                } else {
                    throw new NegocioException("Não foi encontrado nenhuma chave.");
                }
            } else {
                throw new NegocioException("Ambos campos nulos.");
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public boolean inserir(ChaveDTO chave) throws NegocioException {
        boolean resul = false;
        try {
            if (chave.getCod() == null || "".equals(chave.getCod())) {
                throw new NegocioException("Codigo obrigatório.");
            } else if (chave.getSala() == null || "".equals(chave.getSala())) {
                throw new NegocioException("Sala obrigatória.");
            } else if (chave.getCapacidade() == null) {
                throw new NegocioException("Capacidade obrigatório.");
            } else if (chave.getAndar() == null || "".equals(chave.getAndar())) {
                throw new NegocioException("Andar obrigatório.");
            } else if (chave.getTipo() == null || "".equals(chave.getTipo())) {
                throw new NegocioException("Tipo obrigatória.");
            } else {
                ChaveDAO chaveDAO = new ChaveDAO();
                chaveDAO.inserir(chave);
                resul = true;
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }
    
    public boolean deletar(Integer id) throws NegocioException {
        boolean resul = false;
        try {
            if (id == null || id < 1) {
                throw new NegocioException("ID inválido.");
            }
            ChaveDAO dao = new ChaveDAO();
            if (MensagensUtil.confirm("Deseja realmente deletar a chave da sala "+buscarPorId(id).getSala(), "DELETAR")){
                dao.deletar(id);
                resul = true;
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }
    
    public ChaveDTO buscarPorId(Integer id) throws NegocioException {
        try {
            if (id < 1) {
                throw new NegocioException("ID inválido.");
            }
            ChaveDAO dao = new ChaveDAO();
            return dao.buscarPorId(id);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
}
