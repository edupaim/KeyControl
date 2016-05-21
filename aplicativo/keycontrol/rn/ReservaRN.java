package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ReservaDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.dto.IBeneficiarioDTO;
import aplicativo.keycontrol.dto.ProfessorDTO;
import aplicativo.keycontrol.dto.ReservaDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.util.MensagensUtil;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Edu
 */
public class ReservaRN {

    private static ReservaRN singleton;

    private ReservaRN() {
    }

    public static ReservaRN getInstance() {
        if (singleton == null) {
            singleton = new ReservaRN();
            return singleton;
        } else {
            return singleton;
        }
    }

    public boolean fazerReserva(ChaveDTO chave, IBeneficiarioDTO benef, Date dataInicio, Date dataFim, Integer horario) throws NegocioException {
        boolean bool = true;
        try {
            if (benef instanceof ProfessorDTO) {
                List<ReservaDTO> lista = ReservaDAO.getInstance().listarTodos();
                if (lista != null) {
                    for (Integer i = 0; i < lista.size(); i++) {
                        if (chave.getId().equals(lista.get(i).getId_chave())
                                && Objects.equals(horario, lista.get(i).getHorario())
                                && ((dataInicio.getTime() <= lista.get(i).getDate_out().getTime() && dataInicio.getTime() >= lista.get(i).getDate_in().getTime())
                                || (dataFim.getTime() <= lista.get(i).getDate_out().getTime() && dataFim.getTime() >= lista.get(i).getDate_in().getTime()))) {
                            bool = false;
                        }
                    }
                }
                if (bool) {
                    ReservaDAO.getInstance().inserir(new ReservaDTO(null, benef.getId(), chave.getId(), dataInicio, dataFim, horario));
                } else {
                    MensagensUtil.addMsg(null, "Chave já está reservada!");
                }
            } else {
                throw new NegocioException("Permissão negada para reservar chave.");
            }

        } catch (PersistenciaException ex) {
            MensagensUtil.addMsg(null, ex.getMessage());
        }
        return bool;
    }

}
