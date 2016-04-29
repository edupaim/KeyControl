package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.UsuarioDAO;
import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.main.KeyControl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Edu
 */
public class UsuarioRN {

    public UsuarioDTO logar(String login, String senha) throws NegocioException {
        UsuarioDTO usuarioR = null;
        try {
            if (login == null || "".equals(login)) {
                throw new NegocioException("Login obrigatório.");
            } else if (senha == null || "".equals(senha)) {
                throw new NegocioException("Senha obrigatória.");
            } else {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setLogin(login.trim());
                usuario.setSenha(senha.trim());
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioR = usuarioDAO.logar(usuario);
                if (usuarioR != null) {
                    KeyControl.setUsuarioLogado(usuarioR);
                } else {
                    throw new NegocioException("Login ou senha inválido!");
                }
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return usuarioR;
    }

    public boolean inserir(UsuarioDTO u, String senha) throws NegocioException {
        boolean resul = false;
        try {
            if (u.getLogin() == null || "".equals(u.getLogin())) {
                throw new NegocioException("Login obrigatório.");
            } else if (u.getSenha() == null || "".equals(u.getSenha())) {
                throw new NegocioException("Senha obrigatória.");
            } else if (u.getNome() == null || "".equals(u.getNome())) {
                throw new NegocioException("Nome obrigatório.");
            } else if (!u.getSenha().equals(senha)) {
                throw new NegocioException("Repita a senha corretamente.");
            } else {
                u.setLogin(u.getLogin().trim());
                u.setSenha(u.getSenha().trim());
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.inserir(u);
                resul = true;
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }

    public void atualizar(Integer id, UsuarioDTO u) throws NegocioException {
        try {
            if (id == null || id == 0) {
                throw new NegocioException("ID inválido.");
            }
            if (u.getLogin() == null || "".equals(u.getLogin())) {
                throw new NegocioException("Login obrigatório.");
            } else if (u.getSenha() == null || "".equals(u.getSenha())) {
                throw new NegocioException("Senha obrigatória.");
            } else {
                u.setLogin(u.getLogin().trim());
                u.setNome(u.getNome().trim());
                u.setSenha(u.getSenha().trim());
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.atualizar(id, u);
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public void deletar(Integer id) throws NegocioException {
        try {
            if (id == null || id == 0) {
                throw new NegocioException("ID inválido.");
            }
            UsuarioDAO dao = new UsuarioDAO();
            dao.deletar(id);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public List<UsuarioDTO> listarTodos() throws NegocioException {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            return dao.listarTodos();
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public UsuarioDTO buscarPorId(Integer id) throws NegocioException {
        try {
            if (id == 0) {
                throw new NegocioException("ID inválido.");
            }
            UsuarioDAO dao = new UsuarioDAO();
            return dao.buscarPorId(id);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

}
