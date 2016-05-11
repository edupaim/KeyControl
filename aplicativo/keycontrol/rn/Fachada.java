package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.gui.LoginFrame;
import aplicativo.keycontrol.gui.MainFrame;
import aplicativo.keycontrol.main.KeyControl;
import aplicativo.keycontrol.util.MensagensUtil;
import java.awt.Component;
import java.awt.Container;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * @author Edu
 */
public class Fachada {

    // felipe: por que static?
    private static UsuarioRN usuarioRn;

    public Fachada() {
        // felipe: por que não usar this?
        Fachada.usuarioRn = new UsuarioRN();
    }

    /*
     * METODOS DO MAIN FRAME (GERAL)
     */
    public void limparTodosCampos(Container container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) component;
                field.setValue(null);
            } else if (component instanceof JTextField) {
                JTextField field = (JTextField) component;
                field.setText("");
            } else if (component instanceof Container) {
                limparTodosCampos((Container) component);
            }
        }
    }

    public void logout() {
        KeyControl.mainFrame.dispose();
        LoginFrame login = new LoginFrame();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        KeyControl.setUsuarioLogado(null);
    }

    public void atualizarTxtUsuario(Integer id) {
        KeyControl.mainFrame.TxtIdAltC.setText(String.valueOf(id));
        UsuarioRN userRn = new UsuarioRN();
        UsuarioDTO u;
        try {
            u = userRn.buscarPorId(id);
            KeyControl.mainFrame.TxtNomeAltC.setText(u.getNome());
            KeyControl.mainFrame.TxtLoginAltC.setText(u.getLogin());
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }

    }

    public void atualizarTabelaUsuarios() {
        UsuarioRN listaBo = new UsuarioRN();
        List<UsuarioDTO> lista;
        DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblUser.getModel();
        try {
            lista = listaBo.listarTodos();
            while (tbl.getRowCount() > 0) {
                tbl.removeRow(0);
            }
            int i = 0;
            for (UsuarioDTO user : lista) {
                tbl.addRow(new String[1]);
                KeyControl.mainFrame.TblUser.setValueAt(user.getId(), i, 0);
                KeyControl.mainFrame.TblUser.setValueAt(user.getNome(), i, 1);
                KeyControl.mainFrame.TblUser.setValueAt(user.getLogin(), i, 2);
                KeyControl.mainFrame.TblUser.setValueAt(user.getTipoString(), i, 3);
                i++;
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void atualizarTabelaUsuarios(List<UsuarioDTO> consulta) {
        if (consulta != null) {
            DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblUserFiltro.getModel();
            while (tbl.getRowCount() > 0) {
                tbl.removeRow(0);
            }
            int i = 0;
            for (UsuarioDTO user : consulta) {
                tbl.addRow(new String[1]);
                KeyControl.mainFrame.TblUserFiltro.setValueAt(user.getId(), i, 0);
                KeyControl.mainFrame.TblUserFiltro.setValueAt(user.getNome(), i, 1);
                KeyControl.mainFrame.TblUserFiltro.setValueAt(user.getLogin(), i, 2);
                KeyControl.mainFrame.TblUserFiltro.setValueAt((user.getTipoString()), i, 3);
                i++;
            }
        } else {
            atualizarTabelaUsuarios();
        }
    }

    public void listarUsuFiltrado() {
        List<UsuarioDTO> lista;
        UsuarioRN buscarRn = new UsuarioRN();
        try {
            lista = buscarRn.buscar(new UsuarioDTO(KeyControl.mainFrame.TxtIdBusU.getText(),
                    KeyControl.mainFrame.TxtNomeBusU.getText(),
                    KeyControl.mainFrame.TxtLoginBusU.getText(),
                    KeyControl.mainFrame.CBoxTipoBusU.getSelectedIndex()));
            atualizarTabelaUsuarios(lista);
        } catch (NegocioException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * METODOS DO LOGIN FRAME
     */
    public void fazerLogin(String login, String senha) {
        // modifiquei para não precisar de login
        MensagensUtil.addMsg(KeyControl.loginFrame, "Login com sucesso!");
        KeyControl.setUsuarioLogado(new UsuarioDTO(0, "debug", "debug", "debug", 1));
        KeyControl.loginFrame.dispose();
        KeyControl.mainFrame = new MainFrame();
        KeyControl.mainFrame.setLocationRelativeTo(null);
        KeyControl.mainFrame.setVisible(true);
        /*
         try {
         if (usuarioRn.logar(login, senha)) {
         MensagensUtil.addMsg(KeyControl.loginFrame, "Login com sucesso!");
         KeyControl.loginFrame.dispose();
         KeyControl.mainFrame = new MainFrame();
         KeyControl.mainFrame.setLocationRelativeTo(null);
         KeyControl.mainFrame.setVisible(true);
         }
         } catch (NegocioException ex) {
         MensagensUtil.addMsg(KeyControl.loginFrame, ex.getMessage());
         }
         */
    }

    /*
     * METODOS DO MENU MAIN FRAME
     */
    public void menuUsuarios() {
        KeyControl.mainFrame.Painel.removeAll();
        if (KeyControl.getUsuarioLogado().getTipo() > 1) {
            MensagensUtil.addMsg(KeyControl.mainFrame, "Tipo de usuario não permitido.");
        } else {
            KeyControl.mainFrame.Painel.add(KeyControl.mainFrame.AbasUsuarios);
            KeyControl.mainFrame.Painel.repaint();
            KeyControl.mainFrame.Painel.validate();
        }
    }

    /*
     * METODOS DO MAIN FRAME>USUARIO
     */
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
                atualizarTabelaUsuarios();
                limparTodosCampos(KeyControl.mainFrame.Painel);
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha no cadastro.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void tabelaUsuarioSelecionada(Integer linha) {
        KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.AlteraUsuario);
        atualizarTxtUsuario((Integer) KeyControl.mainFrame.TblUser.getValueAt(linha, 0));
    }

    public void alterarUsuario(Integer id, String nome, String login, String senha, Integer tipo, String senhar) {
        try {
            if (usuarioRn.atualizar(new UsuarioDTO(id,
                    nome,
                    login,
                    senha,
                    tipo
            ), senhar)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Alterado com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                atualizarTabelaUsuarios();
                limparTodosCampos(KeyControl.mainFrame.Painel);
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha na alteração.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void excluirUsuario(Integer id) {
        try {
            if (!usuarioRn.deletar(id)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha ao excluir.");
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Excluido com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                KeyControl.fachada.atualizarTabelaUsuarios();
                KeyControl.fachada.limparTodosCampos(KeyControl.mainFrame.Painel);
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }


    /*
     * METODOS DO DEVOLUCAO MAIN FRAME
     */
    public void devolverChave(Integer id) {
        try {
            ChaveDTO chave = new ChaveDTO();
            chave.setId(id);
            ChaveRN.devolucaoChave(chave);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void buscarChave(String cod, String sala) {
        try {
            ChaveDTO chave = new ChaveDTO();
            chave.setCod(cod);
            chave.setSala(sala);
            List<ChaveDTO> chaves = ChaveRN.buscarChave(chave);
            chave = chaves.get(0);
            KeyControl.mainFrame.TxtDevolucaoID.setText(String.valueOf(chave.getId()));
            KeyControl.mainFrame.TxtDevolucaoAndar.setText(chave.getAndar());
            KeyControl.mainFrame.TxtDevolucaoCap.setText(String.valueOf(chave.getCapacidade()));
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }
}
