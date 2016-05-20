package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dao.HistoricoDAO;
import aplicativo.keycontrol.dao.ReservaDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.dto.HistoricoDTO;
import aplicativo.keycontrol.dto.IBeneficiarioDTO;
import aplicativo.keycontrol.dto.ReservaDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.MensagensUtil;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChaveRN {
    /*
     Singleton
     */

    private static ChaveRN singleton;

    private ChaveRN() {
    }

    public static ChaveRN getInstance() {
        if (singleton == null) {
            singleton = new ChaveRN();
            return singleton;
        } else {
            return singleton;
        }
    }

    public void devolucaoChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO DAO = ChaveDAO.getInstance();
        try {
            if (chave.getId() != null) {
                ChaveDTO new_chave;
                if ((new_chave = buscarPorId(chave.getId())) != null && new_chave.getBeneficiario_id() > 0) {
                    chave.setBeneficiario_id(0);
                    DAO.atualizar(chave);
                    HistoricoDAO.getInstance().inserir(new_chave.getBeneficiario_id(), new_chave.getId(), 1);
                } else {
                    throw new NegocioException("Chave já disponivel.");
                }
            } else {
                throw new NegocioException("ID inválido.");
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public void emprestar(ChaveDTO c, IBeneficiarioDTO b) throws NegocioException {
        try {
            boolean bool = true;
            List<ReservaDTO> lista = ReservaDAO.getInstance().listarTodos();
            Timestamp time = new java.sql.Timestamp(new Date().getTime());
            if (!verificarDisponibilidade(c.getId())) {
                throw new NegocioException("Chave não disponível");
            } else {
                for (Integer i = 0; i < lista.size(); i++) {
                    if (c.getId().equals(lista.get(i).getId_chave())
                            /*&& Objects.equals( HORARIO ATUAL , lista.get(i).getHorario())*/
                            && ((time.before(lista.get(i).getDate_out()) && time.after(lista.get(i).getDate_in())))) {
                        bool = false;
                    }
                }
            }
            if (bool) {
                ChaveDAO DAO = ChaveDAO.getInstance();
                ChaveDTO chave = DAO.buscarPorId(c.getId()); //FAZ COM QUE VERIFIQUE A EXISTENCIA DE UMA CHAVE
                chave.setBeneficiario_id(b.getId());
                DAO.atualizar(chave);
                HistoricoDAO.getInstance().inserir(b.getId(), chave.getId(), 0);
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public boolean verificarDisponibilidade(int id) throws NegocioException {

        try {
            ChaveDAO DAO = ChaveDAO.getInstance();
            ChaveDTO c = DAO.buscarPorId(id);
            return (c.getBeneficiario_id() == 0);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }

    }

    public List<ChaveDTO> buscarChave(ChaveDTO chave) throws NegocioException {
        ChaveDAO DAO = ChaveDAO.getInstance();
        List<ChaveDTO> chaves;
        try {
            chaves = DAO.buscar(chave);
            return chaves;
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public boolean inserir(ChaveDTO chave) throws NegocioException {
        boolean resul = false;
        try {
            if (chave.getSala() == null || "".equals(chave.getSala())) {
                throw new NegocioException("Sala obrigatória.");
            } else if (chave.getCapacidade() == null) {
                throw new NegocioException("Capacidade obrigatório.");
            } else if (chave.getTipo() == null) {
                throw new NegocioException("Tipo obrigatório.");
            } else {
                ChaveDAO chaveDAO = ChaveDAO.getInstance();
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
            ChaveDAO dao = ChaveDAO.getInstance();
            if (MensagensUtil.confirm("Deseja realmente deletar a chave da sala " + buscarPorId(id).getSala(), "DELETAR")) {
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
            ChaveDAO dao = ChaveDAO.getInstance();
            return dao.buscarPorId(id);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public boolean atualizar(ChaveDTO chave) throws NegocioException {
        boolean resul = false;
        ChaveDAO chaveDAO = ChaveDAO.getInstance();
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

    List<ChaveDTO> listarTodos() throws NegocioException {
        try {
            return ChaveDAO.getInstance().listarTodos();
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public List<HistoricoDTO> relatorio() throws NegocioException {
        try {
            return HistoricoDAO.getInstance().listarTodos();
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
}
