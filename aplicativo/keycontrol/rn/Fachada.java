package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.AlunoDTO;
import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.dto.ChaveDTO;
import aplicativo.keycontrol.dto.IBeneficiarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.gui.LoginFrame;
import aplicativo.keycontrol.gui.MainFrame;
import aplicativo.keycontrol.main.KeyControl;
import aplicativo.keycontrol.util.MensagensUtil;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
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

    /*
     * METODOS DO LOGIN FRAME
     */
    public void fazerLogin(String login, String senha) {
        /*try {
         if (UsuarioRN.getInstance().logar(login, senha)) {
         MensagensUtil.addMsg(KeyControl.loginFrame, "Login com sucesso!");
         KeyControl.loginFrame.dispose();
         KeyControl.mainFrame = new MainFrame();
         KeyControl.mainFrame.setLocationRelativeTo(null);
         KeyControl.mainFrame.setVisible(true);
         if (KeyControl.getUsuarioLogado().getTipo() > 0) {
         KeyControl.mainFrame.MenuUsuarios.setVisible(false);
         KeyControl.mainFrame.MenuChaves.setVisible(false);
         }
         }
         } catch (NegocioException ex) {
         MensagensUtil.addMsg(KeyControl.loginFrame, ex.getMessage());
         }*/
        KeyControl.setUsuarioLogado(new UsuarioDTO(0, "", "", "", 0));
        KeyControl.loginFrame.dispose();
        KeyControl.mainFrame = new MainFrame();
        KeyControl.mainFrame.setLocationRelativeTo(null);
        KeyControl.mainFrame.setVisible(true);

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
            if (UsuarioRN.getInstance().inserir(
                    new UsuarioDTO(null,
                            nome,
                            login,
                            senha,
                            tipo),
                    senhar)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Cadastro efetuado com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                Fachada.this.preencherTabelaUsuarios();
                limparTodosCampos(KeyControl.mainFrame.Painel);
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha no cadastro.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void preencherTabelaUsuarios() {
        try {
            List<UsuarioDTO> usuarios = UsuarioRN.getInstance().listarTodos();
            DefaultTableModel model = (DefaultTableModel) KeyControl.mainFrame.TblUser.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            usuarios.stream().forEach((user) -> {
                model.addRow(new Object[]{user.getId(), user.getNome(), user.getLogin(), user.getTipoString()});
            });
            KeyControl.mainFrame.TblUser = new JTable(model);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void preencherTabelaUsuariosFiltrado(List<UsuarioDTO> consulta) {
        if (consulta != null) {
            DefaultTableModel model = (DefaultTableModel) KeyControl.mainFrame.TblUserFiltro.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            consulta.stream().forEach((user) -> {
                model.addRow(new Object[]{user.getId(), user.getNome(), user.getLogin(), user.getTipoString()});
            });
            KeyControl.mainFrame.TblUserFiltro = new JTable(model);
        } else {
            Fachada.this.preencherTabelaUsuarios();
        }
    }

    public void buscarUsuariosFiltrado(Integer id, String nome, String login, Integer tipo) {
        List<UsuarioDTO> lista;
        try {
            lista = UsuarioRN.getInstance().buscar(new UsuarioDTO(id,
                    nome,
                    login,
                    null,
                    tipo));
            preencherTabelaUsuariosFiltrado(lista);
        } catch (NegocioException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tabelaUsuarioSelecionada(Integer linha) {
        try {
            if (linha >= 0) {
                UsuarioDTO usuario = UsuarioRN.getInstance().buscarPorId((Integer) KeyControl.mainFrame.TblUser.getModel().getValueAt(linha, 0));
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.AlteraUsuario);
                campoAlterarUsuario(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getTipo());
            }
        } catch (ClassCastException ex) {
            System.out.println("Não foi possivel converter um dos campos. " + ex.getMessage());
        } catch (NegocioException ex) {
        }
    }

    public void campoAlterarUsuario(Integer id, String nome, String login, Integer tipo) {
        KeyControl.mainFrame.TxtIdAltC.setText(String.valueOf(id));
        KeyControl.mainFrame.TxtNomeAltC.setText(nome);
        KeyControl.mainFrame.TxtLoginAltC.setText(login);
        KeyControl.mainFrame.CBoxTipoAaltC.setSelectedIndex(tipo);
    }

    public void alterarUsuario(Integer id, String nome, String login, String senha, Integer tipo, String senhar) {
        try {
            if (UsuarioRN.getInstance().atualizar(new UsuarioDTO(id,
                    nome,
                    login,
                    senha,
                    tipo
            ), senhar)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Alterado com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                Fachada.this.preencherTabelaUsuarios();
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
            if (!UsuarioRN.getInstance().deletar(id)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha ao excluir.");
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Excluido com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                KeyControl.fachada.preencherTabelaUsuarios();
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
            ChaveRN.getInstance().inserir(chave);
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
                model.addRow(new Object[]{chave.getId(), chave.getSala(), chave.getCapacidade(), chave.getTipoString(), chave.getBeneficiario_id()});
            });
            KeyControl.mainFrame.TblChave = new JTable(model);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void excluirChave(Integer id) {
        try {
            if (!ChaveRN.getInstance().deletar(id)) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha ao excluir.");
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Excluido com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                KeyControl.fachada.preencherTabelaUsuarios();
                KeyControl.fachada.limparTodosCampos(KeyControl.mainFrame.Painel);
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void alterarChave(int id, String sala, int capacidade, int tipo) {
        try {
            if (ChaveRN.getInstance().atualizar(new ChaveDTO(id, sala, capacidade, tipo, null))) {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Alterado com sucesso!");
                KeyControl.mainFrame.AbasUsuarios.setSelectedComponent(KeyControl.mainFrame.ListaUsuario);
                Fachada.this.preencherTabelaUsuarios();
                limparTodosCampos(KeyControl.mainFrame.Painel);
            } else {
                MensagensUtil.addMsg(KeyControl.mainFrame, "Falha na alteração.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void listarChaveFiltrado(Integer id, String sala, Integer capacidade, Integer tipo, Integer estado) {
        DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblChave2.getModel();
        if (estado > 0) {
            estado = null;
        }
        ChaveDTO chave = new ChaveDTO(id, sala, capacidade, tipo, estado);
        try {
            List<ChaveDTO> lista = ChaveRN.getInstance().buscarChave(chave);
            while (tbl.getRowCount() > 0) {
                tbl.removeRow(0);
            }
            lista.stream().forEach((c) -> {
                tbl.addRow(new Object[]{c.getId(), c.getSala(), c.getCapacidade(), c.getTipoString(), c.getBeneficiario_id()});
            });
            KeyControl.mainFrame.TblChave = new JTable(tbl);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void tabelaChaveSelecionada(Integer linha) {
        try {
            if (linha >= 0) {
                ChaveDTO chave = ChaveRN.getInstance().buscarPorId((Integer) KeyControl.mainFrame.TblChave.getModel().getValueAt(linha, 0));
                KeyControl.mainFrame.AbasChaves.setSelectedComponent(KeyControl.mainFrame.AlteraChave);
                campoAlterarChave(chave.getId(), chave.getSala(), chave.getCapacidade(), chave.getTipo());
            }
        } catch (ClassCastException ex) {
            System.out.println("Não foi possivel converter um dos campos. " + ex.getMessage());
        } catch (NegocioException ex) {
        }
    }

    private void campoAlterarChave(Integer id, String sala, Integer capacidade, Integer tipo) {
        KeyControl.mainFrame.TxtIdAltC1.setText(String.valueOf(id));
        KeyControl.mainFrame.TxtSalaAltC.setText(sala);
        KeyControl.mainFrame.TxtCapacidadeAltC.setText(String.valueOf(capacidade));
        KeyControl.mainFrame.CBoxTipoAaltC1.setSelectedIndex(tipo);
    }

    /*
     * METODOS DO DEVOLUCAO MAIN FRAME
     */
    public void devolverChave(Integer id) {
        try {
            ChaveDTO chave = new ChaveDTO();
            chave.setId(id);
            ChaveRN.getInstance().devolucaoChave(chave);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void buscarChaveDevolucao(String sala, String capacidade, Integer tipo) {
        try {
            Integer i = 0;
            ChaveDTO chave = new ChaveDTO();
            chave.setSala((sala == null || "".equals(sala)) ? null : sala);
            chave.setCapacidade((capacidade == null || "".equals(capacidade)) ? null : Integer.parseInt(capacidade));
            if (tipo < 3) {
                chave.setTipo(tipo);
            }
            List<ChaveDTO> chaves = ChaveRN.getInstance().buscarChave(chave);
            List<ChaveDTO> lista = new ArrayList<>();
            for (i = 0; i < chaves.size(); i++) {
                if (chaves.get(i).getBeneficiario_id() != 0) {
                    lista.add(chaves.get(i));
                }
            }
            listarChaveDevolucao(lista);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void listarChaveDevolucao(List<ChaveDTO> lista) {
        DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblChaveDev.getModel();
        while (tbl.getRowCount() > 0) {
            tbl.removeRow(0);
        }
        lista.stream().forEach((c) -> {
            tbl.addRow(new Object[]{c.getId(), c.getSala(), c.getCapacidade(), c.getTipoString(), c.getBeneficiario_id()});
        });
        KeyControl.mainFrame.TblChaveDev = new JTable(tbl);
    }

    /*
     * METODOS DO EMPRESTIMO MAIN FRAME
     */
    public void buscarBeneficiarioEmprestimo(String matricula) {
        try {
            IBeneficiarioDTO beneficiario = BeneficiarioRN.getInstance().buscarPorMatricula(matricula);
            KeyControl.mainFrame.TxtEmprestimoNome.setText(beneficiario.getNome());
            String tipo;
            if (beneficiario instanceof AlunoDTO) {
                tipo = "Aluno";
            } else {
                tipo = "Professor";
            }
            KeyControl.mainFrame.TxtEmprestimoTipo.setText(tipo);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void buscarChaveEmprestimo(String sala, String capacidade, Integer tipo) {
        try {
            Integer i = 0;
            ChaveDTO chave = new ChaveDTO();
            chave.setSala((sala == null || "".equals(sala)) ? null : sala);
            chave.setCapacidade((capacidade == null || "".equals(capacidade)) ? null : Integer.parseInt(capacidade));
            if (tipo < 3) {
                chave.setTipo(tipo);
            }
            List<ChaveDTO> chaves = ChaveRN.getInstance().buscarChave(chave);
            List<ChaveDTO> lista = new ArrayList<>();
            for (i = 0; i < chaves.size(); i++) {
                if (chaves.get(i).getBeneficiario_id() == 0) {
                    lista.add(chaves.get(i));
                }
            }
            listarChaveEmprestimo(lista);
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void fazerEmprestimo(Integer id, String matricula) {
        ChaveDTO c;

        try {
            c = ChaveRN.getInstance().buscarPorId(id);
            IBeneficiarioDTO b = BeneficiarioRN.getInstance().buscarPorMatricula(matricula);
            ChaveRN.getInstance().emprestar(c.getId(), b.getId());
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
        }
    }

    public void listarChaveEmprestimo(List<ChaveDTO> lista) {
        DefaultTableModel tbl = (DefaultTableModel) KeyControl.mainFrame.TblChaveEmp.getModel();
        while (tbl.getRowCount() > 0) {
            tbl.removeRow(0);
        }
        lista.stream().forEach((c) -> {
            tbl.addRow(new Object[]{c.getId(), c.getSala(), c.getCapacidade(), c.getTipoString(), c.getBeneficiario_id()});
        });
        KeyControl.mainFrame.TblChaveEmp = new JTable(tbl);
    }
}
