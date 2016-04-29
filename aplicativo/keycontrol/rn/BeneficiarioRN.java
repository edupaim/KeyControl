package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.BeneficiarioDAO;
import aplicativo.keycontrol.dto.BeneficiarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;

public class BeneficiarioRN {

	private BeneficiarioRN(); // padrão singleton

	public static BeneficiarioDTO validarMatricula(int matricula) throws NegocioException {
		try {
			if(matricula == 0 /* || matricula < 100000000 */) 
				throw new NegocioException("Matrícula Inválida.");
			BeneficiarioDTO beneficiario;
			beneficiario = BeneficiarioDAO.validarMatricula(matricula);
			return beneficiario;
		}
		catch (NegocioException | PersistenciaException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
}