/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.gui;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.exception.NegocioException;
import aplicativo.keycontrol.main.KeyControl;
import aplicativo.keycontrol.rn.UsuarioRN;
import aplicativo.keycontrol.util.HoraUtil;
import aplicativo.keycontrol.util.MensagensUtil;
import java.awt.Component;
import java.awt.Container;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edu
 */
public class MainFrame extends javax.swing.JFrame {

    Thread hora = null;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        if (KeyControl.getUsuarioLogado() != null) {
            LbNome.setText(KeyControl.getUsuarioLogado().getNome());
            LbTipo.setText(KeyControl.getUsuarioLogado().getTipoString());
            Painel.removeAll();
        } else {
            MensagensUtil.addMsg(MainFrame.this, "Usuário não logado.");
            MainFrame.this.dispose();
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        }
        iniThreadHora();
    }

    private void iniThreadHora() {
        hora = new Thread(new HoraUtil());
        hora.start();
    }

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
        this.dispose();
        LoginFrame login = new LoginFrame();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        KeyControl.setUsuarioLogado(null);
    }

    public void atualizarTxtUsuario(Integer id) {
        TxtIdAltC.setText(String.valueOf(id));
        UsuarioRN userRn = new UsuarioRN();
        UsuarioDTO u;
        try {
            u = userRn.buscarPorId(id);
            TxtNomeAltC.setText(u.getNome());
            TxtLoginAltC.setText(u.getLogin());
        } catch (NegocioException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarTabelaUsuarios() {
        UsuarioRN listaBo = new UsuarioRN();
        List<UsuarioDTO> lista;
        DefaultTableModel tbl = (DefaultTableModel) TblUser.getModel();
        try {
            lista = listaBo.listarTodos();
            while (tbl.getRowCount() > 0) {
                tbl.removeRow(0);
            }
            int i = 0;
            for (UsuarioDTO user : lista) {
                tbl.addRow(new String[1]);
                TblUser.setValueAt(user.getId(), i, 0);
                TblUser.setValueAt(user.getNome(), i, 1);
                TblUser.setValueAt(user.getLogin(), i, 2);
                TblUser.setValueAt(user.getTipoString(), i, 3);
                i++;
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(MainFrame.this, ex.getMessage());
        }
    }

    public void atualizarTabelaUsuarios(List<UsuarioDTO> consulta) {
        if (consulta != null) {
            DefaultTableModel tbl = (DefaultTableModel) TblUserFiltro.getModel();
            while (tbl.getRowCount() > 0) {
                tbl.removeRow(0);
            }
            int i = 0;
            for (UsuarioDTO user : consulta) {
                tbl.addRow(new String[1]);
                TblUserFiltro.setValueAt(user.getId(), i, 0);
                TblUserFiltro.setValueAt(user.getNome(), i, 1);
                TblUserFiltro.setValueAt(user.getLogin(), i, 2);
                TblUserFiltro.setValueAt((user.getTipoString()), i, 3);
                i++;
            }
        } else {
            MainFrame.this.atualizarTabelaUsuarios();
        }
    }

    public void listarUsuFiltrado() {
        List<UsuarioDTO> lista;
        UsuarioRN buscarRn = new UsuarioRN();
        try {
            lista = buscarRn.busca(TxtIdBusU.getText(),
                    TxtNomeBusU.getText(),
                    TxtLoginBusU.getText(),
                    String.valueOf(CBoxTipoBusU.getSelectedIndex()));
            atualizarTabelaUsuarios(lista);
        } catch (NegocioException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Infos = new javax.swing.JPanel();
        Usuario = new javax.swing.JPanel();
        LbNome = new javax.swing.JLabel();
        Tipo = new javax.swing.JPanel();
        LbTipo = new javax.swing.JLabel();
        Hora = new javax.swing.JPanel();
        LbHora = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        MenuUsuarios = new javax.swing.JButton();
        Painel = new javax.swing.JPanel();
        AbasUsuarios = new javax.swing.JTabbedPane();
        CadastroUsuario = new javax.swing.JPanel();
        LabLoginC = new javax.swing.JLabel();
        LabSenhaC = new javax.swing.JLabel();
        LabSenhaRC = new javax.swing.JLabel();
        LabTipoC = new javax.swing.JLabel();
        LabNomeC = new javax.swing.JLabel();
        TxtNomeCadU = new javax.swing.JTextField();
        TxtUserCadU = new javax.swing.JTextField();
        TxtSenhaCadU = new javax.swing.JPasswordField();
        TxtSenhaRCadU = new javax.swing.JPasswordField();
        CBoxTipoCadU = new javax.swing.JComboBox();
        ButCadastroCadU = new javax.swing.JButton();
        ListaUsuario = new javax.swing.JPanel();
        ScrollPaneTab = new javax.swing.JScrollPane();
        TblUser = new javax.swing.JTable();
        ButAtualizarL = new javax.swing.JButton();
        BuscaUsuario = new javax.swing.JPanel();
        LabIdB = new javax.swing.JLabel();
        LabLoginB = new javax.swing.JLabel();
        LabTipoB = new javax.swing.JLabel();
        LabNomeB = new javax.swing.JLabel();
        TxtIdBusU = new javax.swing.JTextField();
        TxtLoginBusU = new javax.swing.JTextField();
        CBoxTipoBusU = new javax.swing.JComboBox();
        TxtNomeBusU = new javax.swing.JTextField();
        ScrollPaneTab1 = new javax.swing.JScrollPane();
        TblUserFiltro = new javax.swing.JTable();
        ButBuscarBusU = new javax.swing.JButton();
        AlteraUsuario = new javax.swing.JPanel();
        LabIdA = new javax.swing.JLabel();
        LabLoginA = new javax.swing.JLabel();
        LabSenhaNA = new javax.swing.JLabel();
        LabSenhaNRA = new javax.swing.JLabel();
        LabTipoA = new javax.swing.JLabel();
        LabNomeA = new javax.swing.JLabel();
        TxtLoginAltC = new javax.swing.JTextField();
        TxtIdAltC = new javax.swing.JTextField();
        TxtSenhaNAltC = new javax.swing.JPasswordField();
        TxtSenhaN2AltC = new javax.swing.JPasswordField();
        CBoxTipoAaltC = new javax.swing.JComboBox();
        TxtNomeAltC = new javax.swing.JTextField();
        ButExcluirAltC = new javax.swing.JButton();
        ButAlterarAltC = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        ButSair = new javax.swing.JButton();
        ButLogout = new javax.swing.JButton();
        ButLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Usuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuário"));

        LbNome.setText("Nome");

        javax.swing.GroupLayout UsuarioLayout = new javax.swing.GroupLayout(Usuario);
        Usuario.setLayout(UsuarioLayout);
        UsuarioLayout.setHorizontalGroup(
            UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LbNome)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        UsuarioLayout.setVerticalGroup(
            UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LbNome, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        Tipo.setBorder(javax.swing.BorderFactory.createTitledBorder("Perfil"));

        LbTipo.setText("Tipo");

        javax.swing.GroupLayout TipoLayout = new javax.swing.GroupLayout(Tipo);
        Tipo.setLayout(TipoLayout);
        TipoLayout.setHorizontalGroup(
            TipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TipoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LbTipo)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        TipoLayout.setVerticalGroup(
            TipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LbTipo, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        Hora.setBorder(javax.swing.BorderFactory.createTitledBorder("Horário"));

        LbHora.setText("DD/MM/AA hh:mm:ss");

        javax.swing.GroupLayout HoraLayout = new javax.swing.GroupLayout(Hora);
        Hora.setLayout(HoraLayout);
        HoraLayout.setHorizontalGroup(
            HoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LbHora)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        HoraLayout.setVerticalGroup(
            HoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LbHora, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout InfosLayout = new javax.swing.GroupLayout(Infos);
        Infos.setLayout(InfosLayout);
        InfosLayout.setHorizontalGroup(
            InfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfosLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        InfosLayout.setVerticalGroup(
            InfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(InfosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Hora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Menu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Menu"))));

        MenuUsuarios.setText("Usuários");
        MenuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(MenuUsuarios)
                .addContainerGap(640, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuUsuarios)
        );

        Painel.setLayout(new java.awt.CardLayout());

        AbasUsuarios.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        AbasUsuarios.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        AbasUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AbasUsuarios.setPreferredSize(new java.awt.Dimension(500, 600));

        LabLoginC.setText("Usuário");

        LabSenhaC.setText("Senha");

        LabSenhaRC.setText("Repita senha");

        LabTipoC.setText("Tipo");

        LabNomeC.setText("Nome");

        CBoxTipoCadU.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Funcionário" }));
        CBoxTipoCadU.setSelectedIndex(1);

        ButCadastroCadU.setText("Cadastrar");
        ButCadastroCadU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButCadastroCadUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CadastroUsuarioLayout = new javax.swing.GroupLayout(CadastroUsuario);
        CadastroUsuario.setLayout(CadastroUsuarioLayout);
        CadastroUsuarioLayout.setHorizontalGroup(
            CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CadastroUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CadastroUsuarioLayout.createSequentialGroup()
                        .addComponent(LabTipoC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CBoxTipoCadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CadastroUsuarioLayout.createSequentialGroup()
                        .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabLoginC)
                            .addComponent(LabSenhaC)
                            .addComponent(LabNomeC)
                            .addComponent(LabSenhaRC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtSenhaRCadU)
                            .addComponent(TxtUserCadU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(TxtSenhaCadU, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TxtNomeCadU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CadastroUsuarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButCadastroCadU))
        );

        CadastroUsuarioLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TxtNomeCadU, TxtSenhaCadU, TxtSenhaRCadU, TxtUserCadU});

        CadastroUsuarioLayout.setVerticalGroup(
            CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CadastroUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabNomeC)
                    .addComponent(TxtNomeCadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabLoginC)
                    .addComponent(TxtUserCadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabSenhaC)
                    .addComponent(TxtSenhaCadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabSenhaRC)
                    .addComponent(TxtSenhaRCadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CadastroUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabTipoC)
                    .addComponent(CBoxTipoCadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addComponent(ButCadastroCadU))
        );

        AbasUsuarios.addTab("Cadastrar", CadastroUsuario);

        ScrollPaneTab.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Login", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblUserMouseClicked(evt);
            }
        });
        ScrollPaneTab.setViewportView(TblUser);

        ButAtualizarL.setText("Atualizar");
        ButAtualizarL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButAtualizarLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ListaUsuarioLayout = new javax.swing.GroupLayout(ListaUsuario);
        ListaUsuario.setLayout(ListaUsuarioLayout);
        ListaUsuarioLayout.setHorizontalGroup(
            ListaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListaUsuarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButAtualizarL))
            .addGroup(ListaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneTab, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addContainerGap())
        );
        ListaUsuarioLayout.setVerticalGroup(
            ListaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneTab, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButAtualizarL))
        );

        AbasUsuarios.addTab("Listar", ListaUsuario);

        LabIdB.setText("ID");

        LabLoginB.setText("Login");

        LabTipoB.setText("Tipo");

        LabNomeB.setText("Nome");

        TxtIdBusU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIdBusUActionPerformed(evt);
            }
        });

        TxtLoginBusU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtLoginBusUActionPerformed(evt);
            }
        });

        CBoxTipoBusU.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Funcionário", "Nenhum" }));
        CBoxTipoBusU.setSelectedIndex(2);
        CBoxTipoBusU.setToolTipText("");
        CBoxTipoBusU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBoxTipoBusUActionPerformed(evt);
            }
        });

        TxtNomeBusU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomeBusUActionPerformed(evt);
            }
        });

        ScrollPaneTab1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TblUserFiltro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Login", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblUserFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblUserFiltroMouseClicked(evt);
            }
        });
        ScrollPaneTab1.setViewportView(TblUserFiltro);

        ButBuscarBusU.setText("Buscar");
        ButBuscarBusU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButBuscarBusUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BuscaUsuarioLayout = new javax.swing.GroupLayout(BuscaUsuario);
        BuscaUsuario.setLayout(BuscaUsuarioLayout);
        BuscaUsuarioLayout.setHorizontalGroup(
            BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscaUsuarioLayout.createSequentialGroup()
                        .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabIdB)
                            .addComponent(LabLoginB)
                            .addComponent(LabTipoB)
                            .addComponent(LabNomeB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                        .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBoxTipoBusU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNomeBusU, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(TxtLoginBusU)
                            .addComponent(TxtIdBusU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ScrollPaneTab1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscaUsuarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ButBuscarBusU)))
                .addContainerGap())
        );
        BuscaUsuarioLayout.setVerticalGroup(
            BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabIdB)
                    .addComponent(TxtIdBusU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabTipoB)
                    .addComponent(CBoxTipoBusU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabNomeB)
                    .addComponent(TxtNomeBusU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BuscaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabLoginB)
                    .addComponent(TxtLoginBusU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButBuscarBusU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneTab1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        AbasUsuarios.addTab("Buscar", BuscaUsuario);

        LabIdA.setText("ID");

        LabLoginA.setText("Login");

        LabSenhaNA.setText("Nova senha");

        LabSenhaNRA.setText("Repita senha");

        LabTipoA.setText("Tipo");

        LabNomeA.setText("Nome");

        TxtIdAltC.setEditable(false);

        TxtSenhaNAltC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSenhaNAltCActionPerformed(evt);
            }
        });

        CBoxTipoAaltC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Funcionário" }));
        CBoxTipoAaltC.setSelectedIndex(1);

        ButExcluirAltC.setText("Excluir");
        ButExcluirAltC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButExcluirAltCActionPerformed(evt);
            }
        });

        ButAlterarAltC.setText("Alterar");
        ButAlterarAltC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButAlterarAltCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AlteraUsuarioLayout = new javax.swing.GroupLayout(AlteraUsuario);
        AlteraUsuario.setLayout(AlteraUsuarioLayout);
        AlteraUsuarioLayout.setHorizontalGroup(
            AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlteraUsuarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButExcluirAltC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButAlterarAltC))
            .addGroup(AlteraUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlteraUsuarioLayout.createSequentialGroup()
                        .addComponent(LabIdA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtIdAltC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AlteraUsuarioLayout.createSequentialGroup()
                        .addComponent(LabTipoA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CBoxTipoAaltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlteraUsuarioLayout.createSequentialGroup()
                        .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabLoginA)
                            .addComponent(LabNomeA)
                            .addComponent(LabSenhaNRA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                        .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TxtSenhaN2AltC, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNomeAltC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(TxtLoginAltC, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(AlteraUsuarioLayout.createSequentialGroup()
                        .addComponent(LabSenhaNA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtSenhaNAltC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        AlteraUsuarioLayout.setVerticalGroup(
            AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AlteraUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabIdA)
                    .addComponent(TxtIdAltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabTipoA)
                    .addComponent(CBoxTipoAaltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabNomeA)
                    .addComponent(TxtNomeAltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabLoginA)
                    .addComponent(TxtLoginAltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabSenhaNA)
                    .addComponent(TxtSenhaNAltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtSenhaN2AltC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabSenhaNRA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addGroup(AlteraUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButAlterarAltC)
                    .addComponent(ButExcluirAltC)))
        );

        AbasUsuarios.addTab("Alterar", AlteraUsuario);

        Painel.add(AbasUsuarios, "card2");

        ButSair.setText("Sair");
        ButSair.setToolTipText("");
        ButSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButSairActionPerformed(evt);
            }
        });

        ButLogout.setText("Logout");
        ButLogout.setToolTipText("");
        ButLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButLogoutActionPerformed(evt);
            }
        });

        ButLimpar.setText("Limpar");
        ButLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(Infos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButLimpar)
                .addGap(6, 6, 6)
                .addComponent(ButLogout)
                .addGap(6, 6, 6)
                .addComponent(ButSair))
            .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Infos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButLimpar)
                    .addComponent(ButLogout)
                    .addComponent(ButSair)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuUsuariosActionPerformed
        Painel.removeAll();
        if (KeyControl.getUsuarioLogado().getTipo() > 0) {
            MensagensUtil.addMsg(MainFrame.this, "Tipo de usuario não permitido.");
        } else {
            Painel.add(AbasUsuarios);
            Painel.repaint();
            Painel.validate();
        }
    }//GEN-LAST:event_MenuUsuariosActionPerformed

    private void ButCadastroCadUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButCadastroCadUActionPerformed
        KeyControl.fachada.cadastrarUsuario(
                TxtNomeCadU.getText(),
                TxtUserCadU.getText(),
                String.copyValueOf(TxtSenhaCadU.getPassword()),
                String.copyValueOf(TxtSenhaRCadU.getPassword()),
                CBoxTipoCadU.getSelectedIndex());
    }//GEN-LAST:event_ButCadastroCadUActionPerformed

    private void TblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUserMouseClicked
        Integer linha = TblUser.getSelectedRow();
        AbasUsuarios.setSelectedComponent(AlteraUsuario);
        atualizarTxtUsuario((Integer) TblUser.getValueAt(linha, 0));
    }//GEN-LAST:event_TblUserMouseClicked

    private void ButAtualizarLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButAtualizarLActionPerformed
        atualizarTabelaUsuarios();
    }//GEN-LAST:event_ButAtualizarLActionPerformed

    private void ButSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ButSairActionPerformed

    private void ButLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButLogoutActionPerformed
        logout();
    }//GEN-LAST:event_ButLogoutActionPerformed

    private void ButLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButLimparActionPerformed
        limparTodosCampos(Painel);
    }//GEN-LAST:event_ButLimparActionPerformed

    private void TxtIdBusUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIdBusUActionPerformed
        listarUsuFiltrado();
    }//GEN-LAST:event_TxtIdBusUActionPerformed

    private void TxtLoginBusUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtLoginBusUActionPerformed
        listarUsuFiltrado();
    }//GEN-LAST:event_TxtLoginBusUActionPerformed

    private void CBoxTipoBusUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBoxTipoBusUActionPerformed
        listarUsuFiltrado();
    }//GEN-LAST:event_CBoxTipoBusUActionPerformed

    private void TxtNomeBusUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomeBusUActionPerformed
        listarUsuFiltrado();
    }//GEN-LAST:event_TxtNomeBusUActionPerformed

    private void TblUserFiltroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUserFiltroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TblUserFiltroMouseClicked

    private void ButBuscarBusUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButBuscarBusUActionPerformed
        listarUsuFiltrado();
    }//GEN-LAST:event_ButBuscarBusUActionPerformed

    private void ButExcluirAltCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButExcluirAltCActionPerformed
        String id = TxtIdAltC.getText();
        UsuarioRN excluirBo = new UsuarioRN();
        try {
            if (!excluirBo.deletar(Integer.parseInt(id))) {
                MensagensUtil.addMsg(MainFrame.this, "Falha ao excluir.");
            } else {
                MensagensUtil.addMsg(MainFrame.this, "Excluido com sucesso!");
                AbasUsuarios.setSelectedComponent(ListaUsuario);
                atualizarTabelaUsuarios();
                limparTodosCampos(rootPane);
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(MainFrame.this, ex.getMessage());
        }
    }//GEN-LAST:event_ButExcluirAltCActionPerformed

    private void ButAlterarAltCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButAlterarAltCActionPerformed
        UsuarioRN alterarBo = new UsuarioRN();
        try {
            if (alterarBo.atualizar(Integer.parseInt(TxtIdAltC.getText()),
                    new UsuarioDTO(TxtNomeAltC.getText(),
                            TxtLoginAltC.getText(),
                            String.copyValueOf(TxtSenhaNAltC.getPassword()),
                            CBoxTipoAaltC.getSelectedIndex()
                    ), String.copyValueOf(TxtSenhaN2AltC.getPassword()))) {
                MensagensUtil.addMsg(MainFrame.this, "Alterado com sucesso!");
                AbasUsuarios.setSelectedComponent(ListaUsuario);
                atualizarTabelaUsuarios();
                limparTodosCampos(rootPane);
            } else {
                MensagensUtil.addMsg(MainFrame.this, "Falha na alteração.");
            }
        } catch (NegocioException ex) {
            MensagensUtil.addMsg(MainFrame.this, ex.getMessage());
        }
    }//GEN-LAST:event_ButAlterarAltCActionPerformed

    private void TxtSenhaNAltCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSenhaNAltCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSenhaNAltCActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane AbasUsuarios;
    public javax.swing.JPanel AlteraUsuario;
    public javax.swing.JPanel BuscaUsuario;
    public javax.swing.JButton ButAlterarAltC;
    public javax.swing.JButton ButAtualizarL;
    public javax.swing.JButton ButBuscarBusU;
    public javax.swing.JButton ButCadastroCadU;
    public javax.swing.JButton ButExcluirAltC;
    public javax.swing.JButton ButLimpar;
    public javax.swing.JButton ButLogout;
    public javax.swing.JButton ButSair;
    public javax.swing.JComboBox CBoxTipoAaltC;
    public javax.swing.JComboBox CBoxTipoBusU;
    public javax.swing.JComboBox CBoxTipoCadU;
    public javax.swing.JPanel CadastroUsuario;
    public javax.swing.JPanel Hora;
    public javax.swing.JPanel Infos;
    public javax.swing.JLabel LabIdA;
    public javax.swing.JLabel LabIdB;
    public javax.swing.JLabel LabLoginA;
    public javax.swing.JLabel LabLoginB;
    public javax.swing.JLabel LabLoginC;
    public javax.swing.JLabel LabNomeA;
    public javax.swing.JLabel LabNomeB;
    public javax.swing.JLabel LabNomeC;
    public javax.swing.JLabel LabSenhaC;
    public javax.swing.JLabel LabSenhaNA;
    public javax.swing.JLabel LabSenhaNRA;
    public javax.swing.JLabel LabSenhaRC;
    public javax.swing.JLabel LabTipoA;
    public javax.swing.JLabel LabTipoB;
    public javax.swing.JLabel LabTipoC;
    public javax.swing.JLabel LbHora;
    public javax.swing.JLabel LbNome;
    public javax.swing.JLabel LbTipo;
    public javax.swing.JPanel ListaUsuario;
    public javax.swing.JPanel Menu;
    public javax.swing.JButton MenuUsuarios;
    public javax.swing.JPanel Painel;
    public javax.swing.JScrollPane ScrollPaneTab;
    public javax.swing.JScrollPane ScrollPaneTab1;
    public javax.swing.JTable TblUser;
    public javax.swing.JTable TblUserFiltro;
    public javax.swing.JPanel Tipo;
    public javax.swing.JTextField TxtIdAltC;
    public javax.swing.JTextField TxtIdBusU;
    public javax.swing.JTextField TxtLoginAltC;
    public javax.swing.JTextField TxtLoginBusU;
    public javax.swing.JTextField TxtNomeAltC;
    public javax.swing.JTextField TxtNomeBusU;
    public javax.swing.JTextField TxtNomeCadU;
    public javax.swing.JPasswordField TxtSenhaCadU;
    public javax.swing.JPasswordField TxtSenhaN2AltC;
    public javax.swing.JPasswordField TxtSenhaNAltC;
    public javax.swing.JPasswordField TxtSenhaRCadU;
    public javax.swing.JTextField TxtUserCadU;
    public javax.swing.JPanel Usuario;
    public javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
