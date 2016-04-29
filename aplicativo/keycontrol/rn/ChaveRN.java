package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.ChaveDAO;
import aplicativo.keycontrol.dto.BeneficiarioDTO;
import aplicativo.keycontrol.dto.ProfessorDTO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;

public class ChaveRN {

	private ChaveRN();

	public boolean efetuarEmprestimo(BeneficiarioDTO benef, ChaveDTO chave) throws NegocioException {
		List<ChaveDTO> chaves;
		try {
			chaves = buscarChave(chave);
			if(chaves.isEmpty())
				throw new NegocioException("Chave não encontrada.");
			if(chaves.size() > 1)
				throw new NegocioException("Multiplas chaves encontradas.");
			ChaveDTO chaveDAO = chaves.get(0);
			if(chaveDAO.isAvailable(benef, chaveDAO)) 	// checar a instancof de benef;
				return efetuarEmprestimo(benef, chave);
			else
				throw new NegocioException("Chave já emprestada ou reservada.");
		}
		catch (NegocioException | PersistenciaException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	public boolean efetuarReserva(int prof, ChaveDTO chave) throws NegocioException {
		List<ChaveDTO> chaves;
		try {
			chaves = buscarChave(chave);
			if(chaves.isEmpty())
				throw new NegocioException("Chave não encontrada.");
			if(chaves.size() > 1)
				throw new NegocioException("Multiplas chaves encontradas.");
			ChaveDTO chaveDAO = chaves.get(0);
			if(chaveDAO.isReservable(benef, chaveDAO)) 	// checar a instancof de benef;
				return efetuarReserva(benef, chave);
			else
				throw new NegocioException("Chave já emprestada ou reservada.");
		}
		catch (NegocioException | PersistenciaException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	public boolean cadastrarChave(ChaveDTO chave) throws NegocioException;

	public List<ChaveDTO> listarChave() throws NegocioException {
		try {
			List<ChaveDTO> chaves = ChaveDAO.listarTodos();
			return chaves;
		}
		catch (PersistenciaException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}

	public List<ChaveDTO> buscarChave(ChaveDTO chave) throws NegocioException;

	public boolean devolucaoChave(BeneficiarioDTO benef, ChaveDTO chave) throws NegocioException;

	public boolean alterarChave(ChaveDTO chave) throws NegocioException;
}