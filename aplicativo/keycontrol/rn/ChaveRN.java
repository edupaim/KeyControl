package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dto.BeneficiarioDTO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;

public class ChaveRN {

	public boolean efetuarEmprestimo(BeneficiarioDTO benef, ChaveDTO chave) throws NegocioException { /* ... */ }
	
	public boolean efetuarReserva(int prof, ChaveDTO chave) throws NegocioException { /* ... */ }
	
	public boolean cadastrarChave(ChaveDTO chave) throws NegocioException { /* ... */ }

	public List<ChaveDTO> listarChave() throws NegocioException { /* ... */ }

	public boolean buscarChave(ChaveDTO chave) throws NegocioException { /* ... */ }

	public boolean devolucaoChave(BeneficiarioDTO benef, ChaveDTO chave) throws NegocioException { /* ... */ }

	public boolean alterarChave(ChaveDTO chave) throws NegocioException { /* ... */ }
}