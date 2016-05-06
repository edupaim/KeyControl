package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.gui.LoginFrame;
import aplicativo.keycontrol.gui.MainFrame;
import aplicativo.keycontrol.main.KeyControl;
import aplicativo.keycontrol.util.MensagensUtil;
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
    
    public void fazerLogin(String login, String senha){
        try {
            if (usuarioRn.logar(login, senha)) {
                MensagensUtil.addMsg(KeyControl.loginFrame, "Login com sucesso!");
                KeyControl.loginFrame.dispose();
                KeyControl.mainFrame = new MainFrame();
                KeyControl.mainFrame.setLocationRelativeTo(null);
                KeyControl.mainFrame.setVisible(true);
            }
        } catch (NegocioException ex) {
            ex.printStackTrace();
            MensagensUtil.addMsg(KeyControl.loginFrame, ex.getMessage());
        }
    }
}
