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
import java.awt.PopupMenu;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * @author Edu
 */
public class Fachada {

    private static UsuarioRN usuarioRn;

    public Fachada() {
        Fachada.usuarioRn = UsuarioRN.getInstance();
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

    public void campoAlterarUsuario(Integer id, String nome, String login, Integer tipo) {
        KeyControl.mainFrame.TxtIdAltC.setText(String.valueOf(id));
        KeyControl.mainFrame.TxtNomeAltC.setText(nome);
        KeyControl.mainFrame.TxtLoginAltC.setText(login);
        KeyControl.mainFrame.CBoxTipoAaltC.setSelectedIndex(tipo);
    }

    public void buscarUsuarios() {
        UsuarioRN listaBo = UsuarioRN.getInstance();
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

    public void buscarUsuarios(List<UsuarioDTO> consulta) {
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
            Fachada.this.buscarUsuarios();
        }
    }

    public void buscarUsuariosFiltrado() {
        List<UsuarioDTO> lista;
        Integer id;
        if ("".equals(KeyControl.mainFrame.TxtIdBusU.getText())){
            id = null;
        } else {
            id = Integer.parseInt(KeyControl.mainFrame.TxtIdBusU.getText());
        }
        UsuarioRN buscarRn = UsuarioRN.getInstance();
        try {
            lista = buscarRn.buscar(new UsuarioDTO(id,
                    KeyControl.mainFrame.TxtNomeBusU.getText(),
                    KeyControl.mainFrame.TxtLoginBusU.getText(),
                    null,
                    KeyControl.mainFrame.CBoxTipoBusU.getSelectedIndex()));
            buscarUsuarios(lista);
        } catch (NegocioException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * METODOS DO LOGIN FRAME
     */
    public void fazerLogin(String login, String senha) {
         try {
            if (usuarioRn.logar(login, senha)) {
                //MensagensUtil.addMsg(KeyControl.loginFrame, "Login com sucesso!");
                KeyControl.loginFrame.dispose();
                KeyControl.mainFrame = new MainFrame();
                KeyControl.mainFrame.setLocationRelativeTo(null);
                KeyControl.mainFrame.setVisible(true);
            }
         } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.loginFrame, ex.getMessage());
         }
         
    }

    /*
     * METODOS DE PERMISSÃO DO MENU
     * RECEBE UM INTEIRO (NIVEL DO TIPO QUE PODE TER O ACESSO) E 
     * O COMPONENTE QUE VAI SER INSERIDO NO PAINEL...
     */
    public void menuPainel(Integer tipo, Component comp) {
        KeyControl.mainFrame.Painel.removeAll();
        if (KeyControl.getUsuarioLogado().getTipo() > tipo) {
            MensagensUtil.addMsg(null, "Tipo de usuario não permitido.");
        } else {
            KeyControl.mainFrame.Painel.add(comp);
            KeyControl.mainFrame.Painel.repaint();
            KeyControl.mainFrame.Painel.validate();
        }
    }

    /*
     * METODOS DO MAIN FRAME > CRUD USUARIO
     */
    public void cadastrarUsuario(String nome, String login, String senha, String senhar, Integer tipo) {
        try {
            if (usuarioRn.inserir(
                    new UsuarioDTO(null,
                            nome,
                            login,
                            senha,
                            tipo),
                    senhar)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Cadastro efetuado com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                Fachada.this.buscarUsuarios();
                limparTodosCampos(KeyControl.mainFrame.Painel);
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha no cadastro.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void tabelaUsuarioSelecionada(Integer linha) {
        UsuarioDTO usuario = new UsuarioDTO();
        try {
            if(linha >= 0) {
                usuario.setId((Integer) KeyControl.mainFrame.TblUser.getModel().getValueAt(linha, 0));
                usuario.setNome((String) KeyControl.mainFrame.TblUser.getModel().getValueAt(linha, 1));
                usuario.setLogin((String) KeyControl.mainFrame.TblUser.getModel().getValueAt(linha, 2));
                usuario.setTipo((Integer) KeyControl.mainFrame.TblUser.getModel().getValueAt(linha, 3));
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.AlteraUsuario);
                campoAlterarUsuario(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getTipo());
            }
        }
        catch (ClassCastException ex) {
           System.out.println("Não foi possivel converter um dos campos. " + ex.getMessage());
        }
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
                Fachada.this.buscarUsuarios();
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
                KeyControl.fachada.buscarUsuarios();
                KeyControl.fachada.limparTodosCampos(KeyControl.mainFrame.Painel);
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    /*
     *METODOS DO MAINFRAME > CRUD DE CHAVE
     */
    public void inserirChave(String sala, Integer capacidade, Integer tipo) {
        ChaveDTO chave = new ChaveDTO();
        chave.setSala(sala);
        chave.setCapacidade(capacidade);
        chave.setTipo(tipo);
        try {
            ChaveRN.inserir(chave);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void deletarChave(Integer id) {

    }

//    public void listarChaveFiltrado(Integer id, String sala, Integer capacidade, Integer tipo, Integer estado){
//        DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblChave.getModel();
//        if (estado>0){
//            estado = null;
//        }
//        ChaveDTO chave = new ChaveDTO(id, sala, capacidade, tipo, estado);
//        try {
//            List<ChaveDTO> lista = ChaveRN.buscarChave(chave);
//            while (tbl.getRowCount() > 0) {
//                tbl.removeRow(0);
//            }
//            int i = 0;
//            for (ChaveDTO user : lista) {
//                tbl.addRow(new String[1]);
//                KeyControl.mainFrame.TblChave.setValueAt(user.getId(), i, 0);
//                KeyControl.mainFrame.TblChave.setValueAt(user.getSala(), i, 1);
//                KeyControl.mainFrame.TblChave.setValueAt(user.getCapacidade(), i, 2);
//                KeyControl.mainFrame.TblChave.setValueAt(user.getTipoString(), i, 3);
//                KeyControl.mainFrame.TblChave.setValueAt(user.getBeneficiario_id(), i, 3);
//                i++;
//            }
//        } catch (NegocioException ex) {
//            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
//        }
//        
//    }
//    
//    public void listarChave(){
//        DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblChaves.getModel();
//        try {
//            List<ChaveDTO> lista = ChaveRN.buscarChave(new ChaveDTO());
//            while (tbl.getRowCount() > 0) {
//                tbl.removeRow(0);
//            }
//            int i = 0;
//            for (ChaveDTO user : lista) {
//                tbl.addRow(new String[1]);
//                KeyControl.mainFrame.TblChaves.setValueAt(user.getId(), i, 0);
//                KeyControl.mainFrame.TblChaves.setValueAt(user.getSala(), i, 1);
//                KeyControl.mainFrame.TblChaves.setValueAt(user.getCapacidade(), i, 2);
//                KeyControl.mainFrame.TblChaves.setValueAt(user.getTipoString(), i, 3);
//                KeyControl.mainFrame.TblChaves.setValueAt(user.getBeneficiario_id(), i, 3);
//                i++;
//            }
//        } catch (NegocioException ex) {
//            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
//        }
//        
//    }
//    
//    public void alterarChave(Integer id, Integer tipo, String sala, Integer capacidade){
//        try {
//            if (ChaveRN.atualizar(new ChaveDTO(id,
//                    sala,
//                    capacidade,
//                    tipo,
//                    null
//            ))) {
//                MensagensUtil.addMsg(KeyControl.mainFrame, "Alterado com sucesso!");
//                KeyControl.mainFrame.AbasChaves.setSelectedComponent(KeyControl.mainFrame.ListaChave);
//                listarChave();
//                limparTodosCampos(KeyControl.mainFrame.Painel);
//            } else {
//                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha na alteração.");
//            }
//        } catch (NegocioException ex) {
//            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
//        }
//    }
//    
//    public void campoAlterarChave(Integer id) {
//        KeyControl.mainFrame.TxtIdAltChave.setText(String.valueOf(id));
//        ChaveDTO chave;
//        try {
//            chave = ChaveRN.buscarPorId(id);
//            KeyControl.mainFrame.TxtSalaAltChave.setText(chave.getSala());
//            KeyControl.mainFrame.TxtCapacidadeAltChave.setText(String.valueOf(chave.getCapacidade()));
//            KeyControl.mainFrame.CBoxTipoAltChave.setSelectedIndex(chave.getTipo());
//        } catch (NegocioException ex) {
//            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
//        }
//
//    }
    /*
     * METODOS DO DEVOLUCAO MAIN FRAME
     */
    public void devolverChave(String id) {
        try {
            ChaveDTO chave = new ChaveDTO();
            Integer new_id = (id == null || "".equals(id)) ? null : Integer.parseInt(id);
            chave.setId(new_id);
            ChaveRN.devolucaoChave(chave);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void buscarChave(String sala, String capacidade, Integer tipo) {
        try {
            ChaveDTO chave = new ChaveDTO();
            chave.setSala((sala == null || "".equals(sala)) ? null : sala);
            chave.setCapacidade((capacidade == null || "".equals(capacidade)) ? null : Integer.parseInt(capacidade));
            List<ChaveDTO> chaves = ChaveRN.buscarChave(chave);
            chave = chaves.get(0);
            KeyControl.mainFrame.TxtDevolucaoID.setText(String.valueOf(chave.getId()));
            KeyControl.mainFrame.TxtDevolucaoCap.setText(String.valueOf(chave.getCapacidade()));
            KeyControl.mainFrame.TxtDevolucaoSala.setText(chave.getSala());
            KeyControl.mainFrame.ListDevolucaoTipo.setSelectedIndex(chave.getTipo());
            
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void atualizarTabelaUsuarios() {
        try {
            List<UsuarioDTO> usuarios = UsuarioRN.getInstance().listarTodos();
            DefaultTableModel model = (DefaultTableModel) KeyControl.mainFrame.TblUser.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            usuarios.stream().forEach((user) -> {
                model.addRow(new Object[]{user.getId(), user.getNome(), user.getLogin(), user.getTipo()});
            });
            KeyControl.mainFrame.TblUser = new JTable(model);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void atualizarTabelaChaves() {
        try {
            List<ChaveDTO> chaves = ChaveRN.getInstance().listarTodos();
            DefaultTableModel model = (DefaultTableModel) KeyControl.mainFrame.TblChave.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            chaves.stream().forEach((chave) -> {
                model.addRow(new Object[]{chave.getId(), chave.getSala(), chave.getCapacidade(), chave.getTipo(), chave.getBeneficiario_id()});
            });
            KeyControl.mainFrame.TblUser = new JTable(model);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void tabelaChaveSelecionada(Integer linha) {
        ChaveDTO chave = new ChaveDTO();
        try {
            if(linha >= 0) {
                chave.setId((Integer) KeyControl.mainFrame.TblChave.getModel().getValueAt(linha, 0));
                chave.setSala((String) KeyControl.mainFrame.TblChave.getModel().getValueAt(linha, 1));
                chave.setCapacidade((Integer) KeyControl.mainFrame.TblChave.getModel().getValueAt(linha, 2));
                chave.setTipo((Integer) KeyControl.mainFrame.TblChave.getModel().getValueAt(linha, 3));
                KeyControl.mainFrame.AbasChaves.setSelectedComponent(KeyControl.mainFrame.AlteraChave);
                campoAlterarChave(chave.getId(), chave.getSala(), chave.getCapacidade(), chave.getTipo());
            }
        }
        catch (ClassCastException ex) {
           System.out.println("Não foi possivel converter um dos campos. " + ex.getMessage());
        }
    }

    private void campoAlterarChave(Integer id, String sala, Integer capacidade, Integer tipo) {
        KeyControl.mainFrame.TxtIdAltC1.setText(String.valueOf(id));
        KeyControl.mainFrame.TxtSalaAltC.setText(sala);
        KeyControl.mainFrame.TxtCapacidadeAltC.setText(String.valueOf(capacidade));
        KeyControl.mainFrame.CBoxTipoAaltC1.setSelectedIndex(tipo);
    }
}
