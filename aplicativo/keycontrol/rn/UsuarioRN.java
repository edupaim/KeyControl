package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dao.UsuarioDAO;
import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.exception.PersistenciaException;
import aplicativo.keycontrol.main.KeyControl;

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
                }
                else {
                    throw new NegocioException("Login ou senha inválido!");
                }
            }
        } catch (NegocioException | PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return usuarioR;
    }

    public boolean cadastrarUsuario(UsuarioDTO usuario) throws NegocioException { /* ... */ }

    public boolean alterarUsuario(UsuarioDTO usuario) throws NegocioException { /* ... */ }

}
