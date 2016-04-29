package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Edu
 */
public class Fachada {
    private UsuarioRN usuarioRn;

    public Fachada() {
        this.usuarioRn = new UsuarioRN();
    }
    
    public void fazerLogin(UsuarioDTO user){
        try {
            usuarioRn.logar(user.getLogin(), user.getSenha());
        } catch (NegocioException ex) {
        }
    }
}
