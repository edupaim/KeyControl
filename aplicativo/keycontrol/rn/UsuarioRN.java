package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.UsuarioDAO;
import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.main.KeyControl;
import aplicativo.keycontrol.util.MensagensUtil;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Edu
 */
public class UsuarioRN {
    /*
     Singleton
     */

    private static UsuarioRN singleton;

    private UsuarioRN() {
    }

    public static UsuarioRN getInstance() {
        if (singleton == null) {
            singleton = new UsuarioRN();
            return singleton;
        } else {
            return singleton;
        }
    }

    public boolean logar(String login, String senha) throws NegocioException {
        boolean resul = false;
        UsuarioDTO usuarioR;
        try {
            if (login == null || "".equals(login)) {
                throw new NegocioException("Login obrigatório.");
            } else if (senha == null || "".equals(senha)) {
                throw new NegocioException("Senha obrigatória.");
            } else {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setLogin(login.trim());
                usuario.setSenha(senha.trim());
                UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
                usuarioR = usuarioDAO.logar(usuario);
                if (usuarioR != null) {
                    resul = true;
                    KeyControl.setUsuarioLogado(usuarioR);
                } else {
                    throw new NegocioException("Login ou senha inválido!");
                }
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
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
                UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
                usuarioDAO.inserir(u);
                resul = true;
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }

    public boolean atualizar(UsuarioDTO u, String senhar) throws NegocioException {
        boolean resul = false;
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        try {
            if (u.getId() == null || u.getId() < 0) {
                throw new NegocioException("ID inválido.");
            } else if (!u.getSenha().equals(senhar)) {
                throw new NegocioException("Repita a senha nova corretamente.");
            } else {
                u.setLogin(u.getLogin().trim());
                u.setNome(u.getNome().trim());
                u.setSenha(u.getSenha().trim());
                try {
                    usuarioDAO.atualizar(u);
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

    public boolean deletar(Integer id) throws NegocioException {
        boolean resul = false;
        try {
            if (id == null || id == 0) {
                throw new NegocioException("ID inválido.");
            }
            UsuarioDAO dao = UsuarioDAO.getInstance();
            if (MensagensUtil.confirm("Deseja realmente deletar o usario " + buscarPorId(id).getNome(), "DELETAR")) {
                dao.deletar(id);
                resul = true;
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return resul;
    }

    public List<UsuarioDTO> listarTodos() throws NegocioException {
        UsuarioDAO dao = UsuarioDAO.getInstance();
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
            UsuarioDAO dao = UsuarioDAO.getInstance();
            return dao.buscarPorId(id);
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    public List<UsuarioDTO> buscar(UsuarioDTO u) throws NegocioException {
        List<UsuarioDTO> lista = new ArrayList<>();
        UsuarioDAO userDao = UsuarioDAO.getInstance();
        if (u.getLogin() != null && "".equals(u.getLogin())) {
            u.setLogin(null);
        }
        if (u.getNome() != null && "".equals(u.getNome())) {
            u.setNome(null);
        }
        if ((u.getTipo() != null && "".equals(String.valueOf(u.getTipo()))) || u.getTipo() > 1) {
            u.setTipo(null);
        }
        try {
            lista = userDao.buscar(u);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return lista;
    }

}
