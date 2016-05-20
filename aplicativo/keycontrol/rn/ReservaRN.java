package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ReservaDAO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.dto.IBeneficiarioDTO;
import aplicativo.keycontrol.dto.ReservaDTO;
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

    public boolean fazerReserva(ChaveDTO chave, IBeneficiarioDTO benef, Date dataInicio, Date dataFim, Integer horario) {
        boolean bool = true;
        try {
            List<ReservaDTO> lista = ReservaDAO.getInstance().listarTodos();
            if (lista != null) {
                for (Integer i = 0; i < lista.size(); i++) {
                    if (chave.getId().equals(lista.get(i).getId_chave()) 
                            && ((dataInicio.before(lista.get(i).getDate_out()) || dataInicio.equals(lista.get(i).getDate_out()))
                            || dataFim.before(lista.get(i).getDate_out()))
                            && Objects.equals(horario, lista.get(i).getHorario())) {
                        bool = false;
                    }
                }
            }
            if (bool) {
                ReservaDAO.getInstance().inserir(new ReservaDTO(benef.getId(), chave.getId(), dataInicio, dataFim, horario));
            } else {
                MensagensUtil.addMsg(null, "Chave já está reservada!");
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ReservaRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

}
