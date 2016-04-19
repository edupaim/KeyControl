package aplicativo.keycontrol.dao;

import aplicativo.keycontrol.exception.PersistenciaException;
import java.util.List;

public interface GenericoDAO<DTO> {

    void inserir(DTO obj) throws PersistenciaException;

    void atualizar(Integer id, DTO obj) throws PersistenciaException;

    void deletar(Integer id) throws PersistenciaException;

    List<DTO> listarTodos() throws PersistenciaException;

    DTO buscarPorId(Integer id) throws PersistenciaException;

}
