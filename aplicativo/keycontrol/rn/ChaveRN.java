package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dao.UsuarioDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.dto.UsuarioDTO;
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

    public static void devolucaoChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO DAO = new ChaveDAO();
        try {
            if (chave.getId() != null) {
                ChaveDTO new_chave;
                if((new_chave = buscarPorId(chave.getId())) != null && new_chave.getBeneficiario_id() > 0) {
                    chave.setBeneficiario_id(0); // se for null, ele não irá modificar no metodo atualizar
                    DAO.atualizar(chave);
                }
                else {
                    throw new NegocioException("Chave já disponivel.");
                }
            } else {
                throw new NegocioException("ID inválido.");
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public static void emprestar(int idChave, int idBeneficiario) throws NegocioException {
        try {
            if (!verificarDisponibilidade(idChave)) {
                throw new NegocioException("Chave não disponível");
            }

            ChaveDAO DAO = new ChaveDAO();
            ChaveDTO chave = DAO.buscarPorId(idChave); //FAZ COM QUE VERIFIQUE A EXISTENCIA DE UMA CHAVE
            chave.setBeneficiario_id(idBeneficiario);
            DAO.atualizar(chave);
        } catch (NegocioException | PersistenciaException ex) {

        }

        ChaveDAO DAO = new ChaveDAO();

    }

    public static boolean verificarDisponibilidade(int id) throws NegocioException {

        try {
            ChaveDAO DAO = new ChaveDAO();
            ChaveDTO c = DAO.buscarPorId(id);
            return (c.getBeneficiario_id() == null);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }

    }

    public static List<ChaveDTO> buscarChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO DAO = new ChaveDAO();
        List<ChaveDTO> chaves;
        try {
            chaves = DAO.buscar(chave);
            return chaves;
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public static boolean inserir(ChaveDTO chave) throws NegocioException {
        boolean resul = false;
        try {
            if (chave.getSala() == null || "".equals(chave.getSala())) {
                throw new NegocioException("Sala obrigatória.");
            } else if (chave.getCapacidade() == null) {
                throw new NegocioException("Capacidade obrigatório.");
            } else if (chave.getTipo() == null) {
                throw new NegocioException("Tipo obrigatório.");
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

    public static boolean deletar(Integer id) throws NegocioException {
        boolean resul = false;
        try {
            if (id == null || id < 1) {
                throw new NegocioException("ID inválido.");
            }
            ChaveDAO dao = new ChaveDAO();
            if (MensagensUtil.confirm("Deseja realmente deletar a chave da sala " + buscarPorId(id).getSala(), "DELETAR")) {
                dao.deletar(id);
                resul = true;
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }

    public static ChaveDTO buscarPorId(Integer id) throws NegocioException {
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
    
    public static boolean atualizar(ChaveDTO chave) throws NegocioException {
        boolean resul = false;
        ChaveDAO chaveDAO = new ChaveDAO();
        try {
            if (chave.getId() == null || chave.getId() < 0) {
                throw new NegocioException("ID inválido.");
            } else {
                chave.setSala(chave.getSala().trim());
                try {
                    chaveDAO.atualizar(chave);
                } catch (PersistenciaException ex) {
                    throw new NegocioException(ex.getMessage());
                }
                resul = true;
            }
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }
}
