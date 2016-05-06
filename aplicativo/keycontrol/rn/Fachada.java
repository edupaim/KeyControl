package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.gui.MainFrame;
import aplicativo.keycontrol.main.KeyControl;
import aplicativo.keycontrol.util.MensagensUtil;

/*
 * @author Edu
 */
public class Fachada {

    private static UsuarioRN usuarioRn;

    public Fachada() {
        Fachada.usuarioRn = new UsuarioRN();
    }

    public void fazerLogin(String login, String senha) {
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

    public void cadastrarUsuario(String nome, String login, String senha, String senhar, Integer tipo) {
        try {
            if (usuarioRn.inserir(
                    new UsuarioDTO(
                            nome,
                            login,
                            senha,
                            tipo),
                    senhar)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Cadastro efetuado com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                KeyControl.mainFrame.atualizarTabelaUsuarios();
                KeyControl.mainFrame.limparTodosCampos(KeyControl.mainFrame.Painel);
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha no cadastro.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }
}
